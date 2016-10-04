package invader.classes;

import invader.annotation.Invade;
import invader.cache.CacheableObject;

/**
 * Created by Stefan on 9/9/16.
 */
public class FacadeImpl extends CacheableObject implements Facade {

    @Invade
    private PersonService personService;

    @Override
    public void testInjection() {
        personService.find();
    }


}
