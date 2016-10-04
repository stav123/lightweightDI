package dinjection.invader.classes;

import dinjection.invader.cache.CacheableObject;

/**
 * Created by Stefan on 9/8/16.
 */
public class PersonDAOImpl extends CacheableObject implements PersonDAO {

    @Override
    public void create() {
        System.out.println("created");
    }

    @Override
    public void read() {
        System.out.println("read");
    }

    @Override
    public void update() {
        System.out.println("updated");
    }

    @Override
    public void delete() {
        System.out.println("deleted");
    }
}
