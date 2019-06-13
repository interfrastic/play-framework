package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Demonstrate the following relationships in the Ebean ORM:
 * <pre>{@code
 * 1 TO 0 - N RELATIONSHIP
 *
 * Each dorm floor has 0 - N dorm rooms (a floor might have no assignable rooms)
 * Each dorm room has exactly 1 dorm floor
 *
 * +---------------------------------+       +---------------------------------+
 * |                                 |       |                                 |
 * | DormFloor                       |||---O<| DormRoom                        |
 * |                                 |       |                                 |
 * +---------------------------------+       +---------------------------------+
 *
 * @OneToMany(mappedBy = "dormFloor")        @ManyToOne(optional = false)
 * public List<DormRoom> dormRooms;          public DormFloor dormFloor;
 * }</pre>
 * The absence of {@code mappedBy} marks this side of the <!--               -->
 * {@code @OneToMany}/{@code @ManyToOne} relationship as the owning side. The
 * other side of this relationship (the one with {@code mappedBy}) is the
 * inverse (non-owning) side.
 * <p>
 * In order to support this relationship, a column <em>is</em> added to the
 * database table that represents this Java class, as shown below; no column is
 * added to the table representing the other side of the relationship. The
 * presence of {@code optional = false} adds a {@code NOT NULL} constraint to
 * the new column, making it impossible to insert a row representing a dorm room
 * that does not have a dorm floor.
 * <pre>{@code
 * 0 - 1 TO 0 - N RELATIONSHIP
 *
 * Each dorm room has 0 - N students (a room might not be assigned)
 * Each student has 0 - 1 dorm rooms (a student might live off campus)
 *
 * +---------------------------------+       +---------------------------------+
 * |                                 |       |                                 |
 * | DormRoom                        |O|---O<| Student                         |
 * |                                 |       |                                 |
 * +---------------------------------+       +---------------------------------+
 *
 * @OneToMany(mappedBy = "dormRoom")         @ManyToOne
 * public List<Student> students;            public DormRoom dormRoom;
 * }</pre>
 * The presence of {@code mappedBy} marks this side of the <!--              -->
 * {@code @OneToMany}/{@code @ManyToOne} relationship as the inverse
 * (non-owning) side. The other side of this relationship (the one without
 * {@code mappedBy}) is the owning side.
 * <p>
 * In order to support this relationship, a column <em>is not</em> added to the
 * database table that represents this Java class, as shown below; instead, a
 * column is added to the table representing the other side of the relation.
 * <p>
 * Here is the MySQL database table generated by the Ebean ORM:
 * <pre>{@code
 * mysql> DESCRIBE dorm_room;
 * +---------------+--------------+------+-----+---------+----------------+
 * | Field         | Type         | Null | Key | Default | Extra          |
 * +---------------+--------------+------+-----+---------+----------------+
 * | id            | int(11)      | NO   | PRI | NULL    | auto_increment |
 * | name          | varchar(255) | NO   |     | NULL    |                |
 * | dorm_floor_id | int(11)      | NO   | MUL | NULL    |                |
 * +---------------+--------------+------+-----+---------+----------------+
 * 3 rows in set (0.00 sec)
 *
 * }</pre>
 */
@Entity
@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class DormRoom extends DemoModel {

    @ManyToOne(optional = false)
    public DormFloor dormFloor;

    @OneToMany(mappedBy = "dormRoom")
    public List<Student> students;

    public DormRoom(@NotNull String name, @NotNull DormFloor dormFloor) {
        super(name);
        this.dormFloor = dormFloor;
        this.save();
        this.dormFloor.refresh();
    }

    @Override
    @NotNull
    public String getDescription() {
        return getDescriptionFromProperties(
                DemoModel.objectToString(DormFloor.class, dormFloor),
                DemoModel.objectsToString(Student.class, students));
    }

}
