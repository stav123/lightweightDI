import invader.Conf;
import invader.TestController;
import invader.diparser.Invader;

/**
 * Created by Stefan on 9/8/16.
 */
public class App {

    public static void main(String[] args) throws Exception {
        TestController take = Invader.take(new Conf(), TestController.class);
        take.testDAO();
    }

}