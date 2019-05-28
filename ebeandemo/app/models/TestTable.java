package models;

import com.avaje.ebean.Model;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Simple test model to verify Ebean ORM functionality.
 */
@Entity
public class TestTable extends Model {

    @Id
    @GeneratedValue
    public int id;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp testTimestamp;
}
