package dinjection.invader.logger;

/**
 * Created by Stefan on 9/9/16.
 */
public class InvLog {

    private static int dependencyCounter;

    public static void log(Object type) {
        log(type, false);
    }

    public static void log(Object type, boolean fromCache) {

        if(fromCache) {
            System.out.println("dependency from cache of type: " + type.getClass() + " \t hash code = " + type.getClass().hashCode());

        } else {
            System.out.println("# of dependency instantiated: " + ++dependencyCounter
                    + "\t dependency type: " + type.getClass() + " \t hash code = " + type.getClass().hashCode());
        }
    }

}
