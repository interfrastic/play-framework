package controllers;

import java.util.Arrays;
import java.util.Collections;
import javax.validation.constraints.NotNull;
import models.Dorm;
import models.DormFloor;
import models.DormLounge;
import models.DormRoom;
import models.Student;
import models.StudyGroup;
import models.Television;
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
    @NotNull
    public Result index() {

        // https://en.wikipedia.org/wiki/Ada_Lovelace

        Dorm dorm1 = new Dorm("Lovelace Hall");

        String message = "Created " + dorm1 + "\n"
                + dorm1.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Blaise_Pascal

        Dorm dorm2 = new Dorm("Pascal Hall");

        message += "Created " + dorm2 + "\n"
                + dorm2.getDescription() + "\n\n";

        DormFloor dormFloor1 = new DormFloor("L-1", dorm1);

        message += "Created " + dormFloor1 + "\n"
                + dormFloor1.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dorm1.getDescription() + "\n\n";

        DormFloor dormFloor2 = new DormFloor("L-2", dorm1);

        message += "Created " + dormFloor2 + "\n"
                + dormFloor2.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dorm1.getDescription() + "\n\n";

        DormFloor dormFloor3 = new DormFloor("P-1", dorm2);

        message += "Created " + dormFloor3 + "\n"
                + dormFloor3.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dorm2.getDescription() + "\n\n";

        DormFloor dormFloor4 = new DormFloor("P-2", dorm2);

        message += "Created " + dormFloor4 + "\n"
                + dormFloor4.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dorm2.getDescription() + "\n\n";

        DormRoom dormRoom1 = new DormRoom("L-102", dormFloor1);

        message += "Created " + dormRoom1 + "\n"
                + dormRoom1.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor1.getDescription() + "\n\n";

        DormRoom dormRoom2 = new DormRoom("L-103", dormFloor1);

        message += "Created " + dormRoom2 + "\n"
                + dormRoom2.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor1.getDescription() + "\n\n";

        DormRoom dormRoom3 = new DormRoom("L-201", dormFloor2);

        message += "Created " + dormRoom3 + "\n"
                + dormRoom3.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor2.getDescription() + "\n\n";

        DormRoom dormRoom4 = new DormRoom("L-202", dormFloor2);

        message += "Created " + dormRoom4 + "\n"
                + dormRoom4.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor2.getDescription() + "\n\n";

        DormRoom dormRoom5 = new DormRoom("P-201", dormFloor4);

        message += "Created " + dormRoom5 + "\n"
                + dormRoom5.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor4.getDescription() + "\n\n";

        DormRoom dormRoom6 = new DormRoom("P-202", dormFloor4);

        message += "Created " + dormRoom6 + "\n"
                + dormRoom6.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor4.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Grace_Hopper

        Student student1 = new Student("Grace", dormRoom1);

        message += "Created " + student1 + "\n"
                + student1.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom1.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Jean_E._Sammet

        Student student2 = new Student("Jean", dormRoom1);

        message += "Created " + student2 + "\n"
                + student2.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom1.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Frances_E._Allen

        Student student3 = new Student("Frances", dormRoom2);

        message += "Created " + student3 + "\n"
                + student3.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom2.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Charles_Babbage

        Student student4 = new Student("Charles", dormRoom3);

        message += "Created " + student4 + "\n"
                + student4.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom3.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Alan_Turing

        Student student5 = new Student("Alan", dormRoom3);

        message += "Created " + student5 + "\n"
                + student5.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom3.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Sophie_Wilson

        Student student6 = new Student("Sophie", dormRoom4);

        message += "Created " + student6 + "\n"
                + student6.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom4.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Barbara_Liskov

        Student student7 = new Student("Barbara", dormRoom4);

        message += "Created " + student7 + "\n"
                + student7.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom4.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Niklaus_Wirth

        Student student8 = new Student("Niklaus", dormRoom5);

        message += "Created " + student8 + "\n"
                + student8.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom5.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Linus_Torvalds

        Student student9 = new Student("Linus", dormRoom5);

        message += "Created " + student9 + "\n"
                + student9.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormRoom5.getDescription() + "\n\n";

        // https://en.wikipedia.org/wiki/Richard_Stallman

        Student student10 = new Student("Richard");

        message += "Created " + student10 + "\n"
                + student10.getDescription() + "\n\n";

        StudyGroup studyGroup1 = new StudyGroup("Modern COBOL",
                Arrays.asList(student1, student2, student6, student10));

        message += "Created " + studyGroup1 + "\n"
                + studyGroup1.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + student1.getDescription() + "\n\t"
                + student2.getDescription() + "\n\t"
                + student6.getDescription() + "\n\t"
                + student10.getDescription() + "\n\n";

        StudyGroup studyGroup2 = new StudyGroup("Data Structures",
                Arrays.asList(student3, student5, student8));

        message += "Created " + studyGroup2 + "\n"
                + studyGroup2.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + student3.getDescription() + "\n\t"
                + student5.getDescription() + "\n\t"
                + student8.getDescription() + "\n\n";

        StudyGroup studyGroup3 = new StudyGroup("Basic Lisp",
                Arrays.asList(student4, student9, student10));

        message += "Created " + studyGroup3 + "\n"
                + studyGroup3.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + student4.getDescription() + "\n\t"
                + student9.getDescription() + "\n\t"
                + student10.getDescription() + "\n\n";

        DormLounge dormLounge1 = new DormLounge("L-101", dormFloor1,
                Arrays.asList(studyGroup1, studyGroup2));

        message += "Created " + dormLounge1 + "\n"
                + dormLounge1.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor1.getDescription() + "\n\t"
                + studyGroup1.getDescription() + "\n\t"
                + studyGroup2.getDescription() + "\n\n";

        DormLounge dormLounge2 = new DormLounge("P-101", dormFloor3,
                Collections.singletonList(studyGroup3));

        message += "Created " + dormLounge2 + "\n"
                + dormLounge2.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormFloor3.getDescription() + "\n\t"
                + studyGroup3.getDescription() + "\n\n";

        Television television1 = new Television("TV-1", dormLounge2);

        message += "Created " + television1 + "\n"
                + television1.getDescription() + "\n\n"

                + "\tState of related objects:\n\t"
                + dormLounge2.getDescription() + "\n\n";

        Television television2 = new Television("TV-2");

        message += "Created " + television2 + "\n"
                + television2.getDescription() + "\n\n";

        return ok(index.render(message));
    }

}
