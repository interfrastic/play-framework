package models;

import com.avaje.ebean.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * This superclass contains elements common to our college-themed Ebean ORM
 * demo.
 */
@MappedSuperclass
@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class CollegeModel extends Model {

    @Id
    @GeneratedValue
    public int id;

    @NotNull
    public String name;

    public CollegeModel(@NotNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + name
                + " (id " + id + ")";
    }

}
