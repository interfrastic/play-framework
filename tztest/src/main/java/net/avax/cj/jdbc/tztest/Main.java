// Test case for MySQL Bug #90150: getString() retrieves bad DATETIME value
// when client, server time zones differ:
//
// https://bugs.mysql.com/bug.php?id=90150

package net.avax.cj.jdbc.tztest;

// Before running test for first time:
//
// CREATE DATABASE tztest;
// CREATE USER 'tztest'@'localhost' IDENTIFIED BY 'insecure';
// GRANT ALL PRIVILEGES ON tztest.* TO 'tztest'@'localhost' WITH GRANT OPTION;

import java.sql.*;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost"
                + "/tztest?user=tztest&password=insecure&useSSL=false"
                + "&useLegacyDatetimeCode=false");
        String jdbcDriverVersion = c.getMetaData().getDriverVersion();
        Statement s = c.createStatement();

        s.execute("SELECT @@time_zone");

        ResultSet rs = s.getResultSet();

        rs.next();

        String serverTimeZone = rs.getString(1);
        String clientTimeZone = TimeZone.getDefault().toZoneId().toString();

        System.out.println("jdbcDriverVersion:    " + jdbcDriverVersion);
        System.out.println("serverTimeZone:       " + serverTimeZone);
        System.out.println("clientTimeZone:       " + clientTimeZone);

        s = c.createStatement();
        s.execute("DROP TABLE IF EXISTS tztest");
        s.execute("CREATE TABLE tztest (id INTEGER NOT NULL AUTO_INCREMENT,"
                + " as_timestamp DATETIME(3) NOT NULL,"
                + " as_string DATETIME(3) NOT NULL, PRIMARY KEY (id))");

        Timestamp storedAsTimestamp = new Timestamp(System.currentTimeMillis());
        String storedAsString = storedAsTimestamp.toString();
        PreparedStatement ps = c.prepareStatement("INSERT INTO tztest("
                        + "as_timestamp, as_string) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS);

        ps.setTimestamp(1, storedAsTimestamp);
        ps.setString(2, storedAsString);
        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        rs.next();

        int id = rs.getInt(1);

        System.out.println("id:                   " + id);
        System.out.println("storedAsTimestamp:    " + storedAsTimestamp);
        System.out.println("storedAsString:       " + storedAsString);

        ps = c.prepareStatement("SELECT as_timestamp, as_string FROM tztest"
                + " WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        rs = ps.getResultSet();
        rs.next();

        Timestamp retrievedAsTimestamp = rs.getTimestamp(1);
        String retrievedAsString = rs.getString(2);

        System.out.println("retrievedAsTimestamp: " + retrievedAsTimestamp);
        System.out.println("retrievedAsString:    " + retrievedAsString);
    }
}
