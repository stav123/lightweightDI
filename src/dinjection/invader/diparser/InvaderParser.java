package dinjection.invader.diparser;

import dinjection.invader.annotation.Invade;
import dinjection.invader.annotation.InvaderMapping;
import dinjection.invader.annotation.StartInvasion;
import dinjection.invader.cache.Cache;
import dinjection.invader.cache.CacheableObject;
import dinjection.invader.configuration.Configuration;
import dinjection.invader.consts.Messages;
import dinjection.invader.logger.InvLog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Stefan on 9/8/16.
 */
public class InvaderParser {

    private Configuration configuration;

    private final Cache<Class, CacheableObject> cache = new Cache<>(1000, 10, 100);


    public InvaderParser(Configuration configuration) {
        this.configuration = configuration;
        this.configuration.reg();
    }


    public String resolve(String path) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//
//        if(Arrays.asList(configuration.getClass().getAnnotation(StartInvasion.class).classes()).contains(clazz)) {
//            T result = (T) clazz.newInstance();
//            resolveDependencies(result);
//            return result;
//        }

        List<Class> classes = Arrays.asList(configuration.getClass().getAnnotation(StartInvasion.class).classes());

        for(Class aClass : classes) {

            Object o = aClass.newInstance();

            Method[] declaredMethods = o.getClass().getDeclaredMethods();


            for(Method declaredMethod : declaredMethods) {

                if(declaredMethod.isAnnotationPresent(InvaderMapping.class)) {
                    InvaderMapping annotation = declaredMethod.getAnnotation(InvaderMapping.class);
                    if(annotation.value().equals(path)) {
                        resolveDependencies(o);
                        return (String) declaredMethod.invoke(o);
                    }
                }
            }

        }

//        throw new UnsupportedOperationException(
//                String.format(Messages.NOT_PART_OF_REGISTERED_CLASSES, classes.get(0).getName())
//        );
        return "EMPTY";
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

