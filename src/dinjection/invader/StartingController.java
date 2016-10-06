package dinjection.invader;

import com.google.gson.Gson;
import com.sun.deploy.util.StringUtils;
import dinjection.invader.annotation.Invade;
import dinjection.invader.annotation.InvaderMapping;
import dinjection.invader.classes.Person;
import dinjection.invader.classes.PersonService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 9/8/16.
 */
public class StartingController {

    @Invade
    private PersonService personService;


    @InvaderMapping(value = "/")
    public String testDAO() {
        List<Person> personArrayList = new ArrayList<Person>();
        for(int i = 0; i <= 1000; i++) {
            personArrayList.add(new Person("1", "2", "3", "4"));
        }
        Gson gson = new Gson();
        return gson.toJson(personArrayList);
    }

    @InvaderMapping(value = "/test2")
    public String secondTest() {
        personService.find();
        return "person";
    }
}
