package invader.classes;

import invader.annotation.Invade;
import invader.cache.CacheableObject;

/**
 * Created by Stefan on 9/8/16.
 */
public class PersonServiceImpl extends CacheableObject implements PersonService {

    @Invade
    PersonDAO personDAO;

    @Invade
    PersonDAO personDAO2;

    @Override
    public void find() {
        personDAO2.create();
        personDAO.create();
    }


}
