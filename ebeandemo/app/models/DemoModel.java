package models;

import com.avaje.ebean.Model;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * This superclass contains elements common to our Ebean ORM demo.
 */
@MappedSuperclass
@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class DemoModel extends Model {

    @Id
    @GeneratedValue
    public int id;

    @NotNull
    public String name;

    public DemoModel(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    static <T> String objectsToString(@NotNull Class<T> type,
            @NotNull List<T> objects) {
        return objects.isEmpty() ? "no " + type.getSimpleName() + " objects"
                : objects.stream().map(Object::toString)
                        .collect(Collectors.joining(", "));
    }

    @NotNull
    static <T> String objectToString(@NotNull Class<T> type,
            @Nullable T object) {
        return (object == null) ? "no " + type.getSimpleName() + " object"
                : object.toString();
    }

    @NotNull
    public String getDescription() {
        return String.join("\n\n", getObjectDescription(),
                getRelatedObjectsDescription());
    }

    @NotNull
    String getObjectDescription() {
        return getObjectDescriptionFromStrings(Collections.emptyList());
    }

    @NotNull
    String getObjectDescriptionFromStrings(@NotNull List<String> strings) {
        return this + " has "
                + (strings.isEmpty() ? "no properties"
                : String.join(", ", strings));
    }

    @NotNull
    String getRelatedObjectsDescription() {
        return getRelatedObjectsDescriptionFromStrings(Collections.emptyList());
    }

    @NotNull
    String getRelatedObjectsDescriptionFromStrings(
            @NotNull List<String> strings) {
        return "\t" + (strings.isEmpty() ? "No related objects"
                : "State of related objects:\n\t"
                        + String.join("\n\t", strings));
    }

    @Override
    @NotNull
    public String toString() {
        return this.getClass().getSimpleName() + " " + name
                + " (id " + id + ")";
    }

}
