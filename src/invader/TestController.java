package invader;

import invader.annotation.Invade;
import invader.classes.Facade;
import invader.classes.PersonDAO;
import invader.classes.PersonService;

/**
 * Created by Stefan on 9/8/16.
 */
public class TestController {

    @Invade
    private PersonService personService;

    public void testDAO() {
        personService.find();
    }
}
