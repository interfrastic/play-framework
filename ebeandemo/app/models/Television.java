package models;

import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Demonstrate the following relationships in the Ebean ORM:
 * <pre>{@code
 * 0 - 1 TO 0 - 1 RELATIONSHIP
 *
 * Each dorm lounge has 0 - 1 televisions
 * Each television has 0 - 1 dorm lounges (a TV might not be in a lounge)
 *
 * +---------------------------------+       +---------------------------------+
 * |                                 |       |                                 |
 * | DormLounge                      |O|---|O| Television                      |
 * |                                 |       |                                 |
 * +---------------------------------+       +---------------------------------+
 *
 * @OneToOne(mappedBy  = "dormLounge")       @OneToOne
 * public Television television;             public DormLounge dormLounge;
 * }</pre>
 * The absence of {@code mappedBy} marks this side of the <!--               -->
 * {@code @OneToOne}/{@code @OneToOne} relationship as the owning side; it is
 * analogous to {@code @ManyToOne}, the "one" side of a <!--                 -->
 * {@code @OneToMany}/{@code @ManyToOne} relationship, only with the cardinality
 * of the "many" side limited to one. The other side of this relationship (the
 * one with {@code mappedBy}) is the inverse (non-owning) side.
 * <p>
 * In order to support this relationship, a column <em>is</em> added to the
 * database table that represents this Java class, with a uniqueness constraint
 * to limit the cardinality to one, as shown below; no column is added to the
 * table representing the other side of the relationship.
 * <p>
 * Here is the MySQL database table generated by the Ebean ORM:
 * <pre>{@code
 * mysql> DESCRIBE television;
 * +----------------+--------------+------+-----+---------+----------------+
 * | Field          | Type         | Null | Key | Default | Extra          |
 * +----------------+--------------+------+-----+---------+----------------+
 * | id             | int(11)      | NO   | PRI | NULL    | auto_increment |
 * | name           | varchar(255) | NO   |     | NULL    |                |
 * | dorm_lounge_id | int(11)      | YES  | UNI | NULL    |                |
 * +----------------+--------------+------+-----+---------+----------------+
 * 3 rows in set (0.00 sec)
 *
 * }</pre>
 */
@Entity
@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class Television extends DemoModel {

    @OneToOne // Default: optional = true.
    public DormLounge dormLounge;

    public Television(@NotNull String name) {
        super(name);
        this.save();
    }

    public Television(@NotNull String name, @NotNull DormLounge dormLounge) {
        super(name);
        this.dormLounge = dormLounge;
        this.save();
        this.dormLounge.refresh();
    }

    @Override
    @NotNull
    public String getObjectDescription() {
        return getObjectDescriptionFromStrings(Collections.singletonList(
                DemoModel.objectToString(DormLounge.class, dormLounge)));
    }

    @Override
    @NotNull
    public String getRelatedObjectsDescription() {
        return (dormLounge == null) ? super.getRelatedObjectsDescription()
                : getRelatedObjectsDescriptionFromStrings(
                        Collections.singletonList(
                                dormLounge.getObjectDescription()));
    }

}