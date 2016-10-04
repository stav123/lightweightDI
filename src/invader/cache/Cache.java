package invader.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Stefan on 9/17/16.
 */
public class Cache<K, T extends CacheableObject> {

    private long timeToLive;
    private final Map<K, T> cacheMap;

    public Cache(long timeToLive, final long checkingInterval, int maxNumberOfCachedItems) {
        this.timeToLive = timeToLive * 2000;

        cacheMap = new HashMap<K, T>(maxNumberOfCachedItems);

        if(timeToLive > 0 && checkingInterval > 0) {

            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(checkingInterval * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    // PUT method
    public void put(K key, T value) {
        synchronized (cacheMap) {
            cacheMap.put(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (cacheMap) {
            T c = cacheMap.get(key);

            if(c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return (T) c;
            }
        }
    }

    // REMOVE method
    public void remove(String key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }

    // Get Cache Objects Size()
    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    // CLEANUP method
    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<String> deleteKey = null;

        synchronized (cacheMap) {
            Iterator<?> itr = cacheMap.entrySet().iterator();

            deleteKey = new ArrayList<String>((cacheMap.size() / 2) + 1);
            CacheableObject c = null;

            while (itr.hasNext()) {
                String key = (String) itr.next();
                c = (CacheableObject) ((Map.Entry<?, ?>) itr).getValue();
                if(c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }

        for(String key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }

            Thread.yield();
        }
    }
}
