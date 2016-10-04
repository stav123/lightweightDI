package dinjection.invader.diparser;

import dinjection.invader.StartingController;
import dinjection.invader.annotation.StartInvasion;
import dinjection.invader.configuration.Configuration;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Stefan on 9/8/16.
 */
@StartInvasion(classes = {
        StartingController.class
        }
)
public class Invader {

    public static <T> T take(Configuration configuration, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        configuration.reg();
        return new InvaderParser(configuration).resolve(clazz);
    }
}
