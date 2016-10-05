package dinjection.invader;

import dinjection.invader.annotation.Invade;
import dinjection.invader.annotation.InvaderMapping;
import dinjection.invader.classes.PersonService;

/**
 * Created by Stefan on 9/8/16.
 */
public class StartingController {

    @Invade
    private PersonService personService;

    @InvaderMapping(value = "/dao")
    public String testDAO() {
        return "test";
    }

    @InvaderMapping(value = "/test2")
    public String secondTest() {
        personService.find();
        return "person";
    }
}
