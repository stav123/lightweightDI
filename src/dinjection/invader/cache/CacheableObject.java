package dinjection.invader.cache;

/**
 * Created by Stefan on 9/17/16.
 */
public abstract class CacheableObject {
    public long lastAccessed = System.currentTimeMillis();
}
