package invader.classes;

import invader.cache.CacheableObject;

/**
 * Created by Stefan on 9/9/16.
 */
public class PersonDAOImpl2 extends CacheableObject implements PersonDAO {

    @Override
    public void create() {
        System.out.println("from 2");
    }

    @Override
    public void read() {
        System.out.println("from 2");

    }

    @Override
    public void update() {
        System.out.println("from 2");

    }

    @Override
    public void delete() {
        System.out.println("from 2");

    }
}
