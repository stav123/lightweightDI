package invader;

import invader.annotation.StartInvasion;
import invader.classes.*;
import invader.configuration.DefaultConfiguration;

/**
 * Created by Stefan on 9/9/16.
 */
@StartInvasion(classes = {
        TestController.class
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
