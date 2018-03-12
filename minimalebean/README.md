# minimalebean

Example of a minimal Play Framework IntelliJ project using the Ebean ORM to
connect to a MySQL database through the MySQL Connector/J JDBC driver.

## Steps to create

1. Determine the version of the most recent generally available (GA) release of
   the Play Framework Ebean plugin, which may be found
   [here](https://github.com/playframework/play-ebean#releases).

1. Add the following line at the end of `project/plugins.sbt`, replacing `4.1.0`
   with the current plugin version:

   ```scala
   addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "4.1.0")
   ```
   
   Save the file. If you see a __plugins.sbt was changed__ message, press
   __Refresh project__; do _not_ attempt to enable auto-import.
   
   If the message “sbt compilation for play framework 2.x disabled by default”
   appears, you may safely ignore it.

1. Determine the version of the most recent generally available (GA) release of
   the MySQL Connector/J, which may be found
   [here](https://dev.mysql.com/downloads/connector/j/#current).

1. Update your `build.sbt` file.

   1. Locate the following line:

      ```scala
      lazy val root = (project in file(".")).enablePlugins(PlayJava)
      ```

      Change the line as follows:
   
      ```scala
      lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
      ```
      
   1. Add the following lines at the end, replacing `5.1.41` with the current
      connector version:

      ```scala
      libraryDependencies += javaJdbc
      libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
      ```

   1. Save the file. If you see a __build.sbt was changed__ message, press
      __Refresh project__; do _not_ attempt to enable auto-import.
      
   1. If the message “sbt compilation for play framework 2.x disabled by
      default” appears, you may safely ignore it.

1. Add the following lines to the end of your `conf/application.conf` file, 
   replacing the example values with those for your database, and save the
   file:
 
   ```ini
   # Default database configuration using MySQL database engine
   # Connect to playdb as playdbuser
   db.default.driver="com.mysql.jdbc.Driver"
   db.default.url="jdbc:mysql://localhost/playdb"
   db.default.username="playdbuser"
   db.default.password="a strong password"
   ebean.default = ["models.*"]
   ```

1. Add the following lines to the end of the `.gitignore` file at the root of
   your Play project:

```ini
# Ignore generated Ebean evolutions.

/conf/evolutions
```

1. In the __Project__ pane, right-click (or Control-click on macOS) __app__,
   select __New ▶︎ Package__, and enter __models__.

1. In the __Project__ pane, right-click (or Control-click on macOS) __models__,
   select __New ▶︎ Java Class__, and enter a name; for example, __TestTable__.

1. Edit __TestTable.java__ as follows:

   ```java
   package models;

   import io.ebean.Model;
   import io.ebean.annotation.NotNull;

   import javax.persistence.Column;
   import javax.persistence.Entity;
   import javax.persistence.GeneratedValue;
   import javax.persistence.Id;
   import java.sql.Timestamp;

   @Entity
   public class TestTable extends Model {

       @Id
       @GeneratedValue
       public int id;

       @NotNull
       @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
       public Timestamp testTimestamp;
   }
   ```

1. Create the MySQL database you specified in `application.conf` if it does not
   exist already; for example:

   ```console
   $ mysql --user=root --execute='CREATE DATABASE playdb;'
   ```

1. Run the application using the __Play 2__ configuration.

1. When you see the __Database 'default' needs evolution!__ page in your
   browser, press the __Apply this script now!__ button.

1. If everything worked correctly, your browser will display a
   __Welcome to Play!__ page; at this point, you can examine the new table in
   the database:

   ```console   
   $ mysql --user=root --database=playdb --execute='DESCRIBE test_table;'
   +----------------+-----------+------+-----+-------------------+----------------+
   | Field          | Type      | Null | Key | Default           | Extra          |
   +----------------+-----------+------+-----+-------------------+----------------+
   | id             | int(11)   | NO   | PRI | NULL              | auto_increment |
   | test_timestamp | timestamp | NO   |     | CURRENT_TIMESTAMP |                |
   +----------------+-----------+------+-----+-------------------+----------------+
   ```
