package dinjection.invader.configuration;


import dinjection.invader.consts.Messages;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan on 9/8/16.
 */
abstract public class DefaultConfiguration implements Configuration {

    private Map<Class<?>, Class<?>> classMap = new HashMap<Class<?>, Class<?>>();

    public <T> void createMapping(Class<T> baseClass) {
        classMap.put(baseClass, baseClass);
    }

    public <T> void createMapping(Class<T> parentClass, Class<? extends T> childClass) {
        classMap.put(parentClass, childClass.asSubclass(parentClass));
    }


    public <T> Class<? extends T> getMapping(Class<T> baseClass) {

        Class<?> subClass = classMap.get(baseClass);

        if(subClass == null)
            throw new IllegalArgumentException(
                    String.format(Messages.NO_REGISTERED_MAPPING, baseClass.getName())
            );

        return subClass.asSubclass(baseClass);
    }
}
