package invader.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Stefan on 9/10/16.
 */

@Retention(RetentionPolicy.RUNTIME) //tag to register "starting" classes and loading them in classLoader
public @interface StartInvasion {

    Class[] classes();
}
