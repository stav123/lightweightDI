package dinjection.invader.diparser;

import dinjection.invader.annotation.Invade;
import dinjection.invader.annotation.StartInvasion;
import dinjection.invader.cache.Cache;
import dinjection.invader.cache.CacheableObject;
import dinjection.invader.configuration.Configuration;
import dinjection.invader.consts.Messages;
import dinjection.invader.logger.InvLog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by Stefan on 9/8/16.
 */
public class InvaderParser {

    private Configuration configuration;

    private final Cache<Class, CacheableObject> cache = new Cache<>(1000, 10, 100);


    InvaderParser(Configuration configuration) {
        this.configuration = configuration;
    }


    <T> T resolve(Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        if(Arrays.asList(configuration.getClass().getAnnotation(StartInvasion.class).classes()).contains(clazz)) {
            T result = (T) clazz.newInstance();
            resolveDependencies(result);
            return result;
        }

        throw new UnsupportedOperationException(
                String.format(Messages.NOT_PART_OF_REGISTERED_CLASSES, clazz.getName())
        );
    }


    @SuppressWarnings("unchecked")
    private void resolveDependencies(Object object) throws IllegalAccessException, InstantiationException {

        Object targetClassInstance = null;

        Field[] currentDependencies = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(c -> c.isAnnotationPresent(Invade.class))
                .toArray(Field[]::new);

        for(Field dependency : currentDependencies) {
            dependency.setAccessible(true);

            Class<?> sourceClass = dependency.getType();
            Class<?> targetClass = configuration.getMapping(sourceClass);

            if(cache.get(targetClass) != null) {
                targetClassInstance = cache.get(targetClass);
                InvLog.log(targetClassInstance, true);
            } else {
                targetClassInstance = targetClass.newInstance();
                cache.put(targetClass, (CacheableObject) targetClassInstance);
                InvLog.log(targetClassInstance);
            }

            dependency.set(object, targetClassInstance);
            this.resolveDependencies(targetClassInstance);
        }
    }
}

