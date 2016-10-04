package invader.configuration;

/**
 * Created by Stefan on 9/8/16.
 */
public interface Configuration {

    void reg();

     <T> Class<? extends T> getMapping(Class<T> baseClass);



}
