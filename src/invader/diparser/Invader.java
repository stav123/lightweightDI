package invader.diparser;

import invader.annotation.StartInvasion;
import invader.configuration.Configuration;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Stefan on 9/8/16.
 */
@StartInvasion(classes = {
        Integer.class
        }
)
public class Invader {

    public static <T> T take(Configuration configuration, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        configuration.reg();
        return new InvaderParser(configuration).resolve(clazz);
    }
}
