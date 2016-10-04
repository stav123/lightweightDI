package dinjection.invader.classes;

import dinjection.invader.annotation.Invade;
import dinjection.invader.cache.CacheableObject;

/**
 * Created by Stefan on 9/8/16.
 */
public class PersonServiceImpl extends CacheableObject implements PersonService {

    @Invade
    private PersonDAO personDAO;

    @Invade
    private PersonDAO personDAO2;

    @Override
    public void find() {
        personDAO2.create();
        personDAO.create();
    }


}
