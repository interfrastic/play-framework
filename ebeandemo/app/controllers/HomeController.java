package controllers;

import java.util.ArrayList;
import java.util.List;
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
@SuppressWarnings("WeakerAccess")
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        // Test out the relationships.

        List<String> lines = new ArrayList<>();

        DormRoom dormRoom = new DormRoom("101");

        lines.add("Created " + dormRoom);
        lines.add(dormRoom.getDescription());

        ParkingSpace parkingSpace = new ParkingSpace("A1");

        lines.add(parkingSpace.getDescription());

        Student student1 = new Student("Homer", dormRoom, parkingSpace);

        lines.add("Created " + student1);
        lines.add(student1.getDescription());

        lines.add(dormRoom.getDescription());
        lines.add(parkingSpace.getDescription());

        Student student2 = new Student("Marge", dormRoom, null);

        lines.add("Created " + student2);
        lines.add(student2.getDescription());

        lines.add(dormRoom.getDescription());
        lines.add(parkingSpace.getDescription());

        return ok(index.render(String.join("\n", lines)));
    }

}
