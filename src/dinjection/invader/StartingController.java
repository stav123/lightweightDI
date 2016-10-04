package dinjection.invader;

import dinjection.invader.annotation.Invade;
import dinjection.invader.classes.PersonService;

/**
 * Created by Stefan on 9/8/16.
 */
public class StartingController {

    @Invade
    private PersonService personService;

    public void testDAO() {
        personService.find();
    }
}
