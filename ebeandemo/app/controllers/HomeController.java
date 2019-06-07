package controllers;

import models.DormRoom;
import models.ParkingSpace;
import models.Student;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        // Test out the relationships.

        DormRoom dormRoom = new DormRoom("101");

        String message = "Created " + dormRoom + "\n"
                + dormRoom.getDescription() + "\n\n";

        ParkingSpace parkingSpace = new ParkingSpace("A1");

        message += "Created " + parkingSpace + "\n"
                + parkingSpace.getDescription() + "\n\n";

        Student student1 = new Student("Homer", dormRoom, parkingSpace);

        message += "Created " + student1 + "\n"
                + student1.getDescription() + "\n\n"

                + "State of related objects:\n"
                + dormRoom.getDescription() + "\n"
                + parkingSpace.getDescription() + "\n\n";

        Student student2 = new Student("Marge", dormRoom, null);

        message += "Created " + student2 + "\n"
                + student2.getDescription() + "\n\n"

                + "State of related objects:\n"
                + dormRoom.getDescription() + "\n"
                + parkingSpace.getDescription();

        return ok(index.render(message));
    }

}
