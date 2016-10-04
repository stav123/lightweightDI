package dinjection.invader;

import dinjection.invader.annotation.StartInvasion;
import dinjection.invader.classes.*;
import dinjection.invader.configuration.DefaultConfiguration;

/**
 * Created by Stefan on 9/9/16.
 */
@StartInvasion(classes = {
        StartingController.class
}
)
public class Conf extends DefaultConfiguration {

    @Override
    public void reg() {
        createMapping(PersonDAO.class, PersonDAOImpl.class);
        createMapping(Facade.class, FacadeImpl.class);
        createMapping(PersonService.class, PersonServiceImpl.class);
    }
}
