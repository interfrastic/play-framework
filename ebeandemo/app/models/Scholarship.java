package models;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * Demonstrate the following relationships in the Ebean ORM:
 * <pre>{@code
 * Each scholarship has 0 - N students
 * Each student has 0 - N scholarships
 *
 * +---------------------------------+       +---------------------------------+
 * | Scholarship                     |>O---O<| Student                         |
 * +---------------------------------+       +---------------------------------+
 *
 * @ManyToMany(mappedBy                      @ManyToMany
 *         = "scholarships")                 public List<Scholarship>
 * public List<Student> students;                     scholarships;
 * }</pre>
 * The presence of {@code mappedBy} marks this side of the <!--              -->
 * {@code @ManyToMany}/{@code @ManyToMany} relationship as the inverse
 * (non-owning) side; the other side is the owning side.
 * <p>
 * In order to support this relationship, no columns are added to the two
 * database tables that represent its Java classes; instead, an auxiliary join
 * table named {@code student_scholarship} is added to the database, as shown
 * below.
 * <p>
 * Here are the MySQL database table and the auxiliary join table generated by
 * the Ebean ORM; the first column in the join table belongs to the owning side
 * of the relationship and the second column belongs to the inverse (non-owning)
 * side:
 * <pre>{@code
 * mysql> DESCRIBE scholarship;
 * +-------+--------------+------+-----+---------+----------------+
 * | Field | Type         | Null | Key | Default | Extra          |
 * +-------+--------------+------+-----+---------+----------------+
 * | id    | int(11)      | NO   | PRI | NULL    | auto_increment |
 * | name  | varchar(255) | YES  |     | NULL    |                |
 * +-------+--------------+------+-----+---------+----------------+
 * 2 rows in set (0.00 sec)
 *
 * mysql> DESCRIBE student_scholarship;
 * +----------------+---------+------+-----+---------+-------+
 * | Field          | Type    | Null | Key | Default | Extra |
 * +----------------+---------+------+-----+---------+-------+
 * | student_id     | int(11) | NO   | PRI | NULL    |       |
 * | scholarship_id | int(11) | NO   | PRI | NULL    |       |
 * +----------------+---------+------+-----+---------+-------+
 * 2 rows in set (0.00 sec)
 *
 * }</pre>
 */
@Entity
@SuppressWarnings("WeakerAccess")
public class Scholarship extends CollegeModel {

    @ManyToMany(mappedBy = "scholarships") // Default: optional = true.
    @SuppressWarnings("unused")
    public List<Student> students;

    public Scholarship(String name) {
        super(name);
        this.save();
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder(this.toString());

        sb.append(" has ");

        if (students.isEmpty()) {
            sb.append("no ").append(Student.class.getSimpleName())
                    .append(" objects");
        } else {
            sb.append(students.stream().map(Objects::toString)
                    .collect(Collectors.joining(", ")));
        }

        return sb.toString();
    }

}
