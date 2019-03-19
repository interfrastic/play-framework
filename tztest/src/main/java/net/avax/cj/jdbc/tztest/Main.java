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
        Boolean noDatetimeStringSync = true;
        Boolean useLegacyDatetimeCode = false;

        String url = "jdbc:mysql://localhost/tztest?user=tztest"
                + "&password=insecure&useSSL=false";

        if (noDatetimeStringSync != null) {
            url += "&noDatetimeStringSync=" + noDatetimeStringSync;
        }

        if (useLegacyDatetimeCode != null) {
            url += "&useLegacyDatetimeCode=" + useLegacyDatetimeCode;
        }

        Connection conn = DriverManager.getConnection(url);
        DatabaseMetaData meta = conn.getMetaData();
        Statement s = conn.createStatement();
        ResultSet rs;

        if (!s.execute("SELECT @@time_zone")
                || !(rs = s.getResultSet()).next()) {
            throw new AssertionError("Failed to select server time zone");
        }

        String serverTimeZone = rs.getString(1);

        String clientTimeZone = TimeZone.getDefault().toZoneId().toString();

        System.out.println("jdbcDriverVersion:           "
                + meta.getDriverVersion());
        System.out.println("useLegacyDatetimeCode:       "
                + useLegacyDatetimeCode);
        System.out.println("serverTimeZone:              "
                + serverTimeZone);
        System.out.println("clientTimeZone:              "
                + clientTimeZone);

        s = conn.createStatement();

        s.execute("DROP TABLE IF EXISTS tztest");

        s.execute("CREATE TABLE tztest (\n"
                + "    id INT(11) NOT NULL AUTO_INCREMENT,\n"
                + "    date_as_date DATE NOT NULL,\n"
                + "    datetime_as_timestamp DATETIME(6) NOT NULL,\n"
                + "    datetime_as_string DATETIME(6) NOT NULL,\n"
                + "    timestamp_from_client TIMESTAMP(6) NOT NULL,\n"
                + "    timestamp_from_server TIMESTAMP(6)\n"
                + "        DEFAULT CURRENT_TIMESTAMP(6),\n"
                + "    PRIMARY KEY (id)\n"
                + ")");

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        Timestamp timestamp = new Timestamp(now);

        Date insertedDateAsDate = date;
        String insertedDatetimeAsString = timestamp.toString();
        Timestamp insertedDatetimeAsTimestamp = timestamp;
        Timestamp insertedTimestampFromClient = timestamp;

        PreparedStatement ps = conn.prepareStatement("INSERT INTO tztest(\n"
                        + "    date_as_date,\n"
                        + "    datetime_as_timestamp,\n"
                        + "    datetime_as_string,\n"
                        + "    timestamp_from_client\n"
                        + ") VALUES (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?\n"
                        + ")",
                Statement.RETURN_GENERATED_KEYS);

        ps.setDate(1, insertedDateAsDate);
        ps.setTimestamp(2, insertedDatetimeAsTimestamp);
        ps.setString(3, insertedDatetimeAsString);
        ps.setTimestamp(4, insertedTimestampFromClient);

        int rowCount = ps.executeUpdate();

        rs = ps.getGeneratedKeys();

        if (rowCount != 1 || !rs.next()) {
            throw new AssertionError("Failed to insert row");
        }

        int insertedRowId = rs.getInt(1);

        System.out.println();
        System.out.println("insertedRowId:               "
                + insertedRowId);
        System.out.println("insertedDateAsDate:          "
                + insertedDateAsDate);
        System.out.println("insertedDatetimeAsTimestamp: "
                + insertedDatetimeAsTimestamp);
        System.out.println("insertedDatetimeAsString:    "
                + insertedDatetimeAsString);
        System.out.println("insertedTimestampFromClient: "
                + insertedTimestampFromClient);

        ps = conn.prepareStatement("SELECT\n"
                + "    date_as_date,\n"
                + "    datetime_as_timestamp,\n"
                + "    datetime_as_string,\n"
                + "    timestamp_from_client,\n"
                + "    timestamp_from_server\n"
                + "FROM tztest WHERE id = ?");

        ps.setInt(1, insertedRowId);

        if (!ps.execute() || !(rs = ps.getResultSet()).next()) {
            throw new AssertionError("Failed to select inserted row");
        }

        Date selectedDateAsDate = rs.getDate(1);
        Timestamp selectedDatetimeAsTimestamp = rs.getTimestamp(2);
        String selectedDatetimeAsString = rs.getString(3);
        Timestamp selectedTimestampFromClient = rs.getTimestamp(4);
        Timestamp selectedTimestampFromServer = rs.getTimestamp(5);

        System.out.println();
        System.out.println("selectedDateAsDate:          "
                + selectedDateAsDate);
        System.out.println("selectedDatetimeAsTimestamp: "
                + selectedDatetimeAsTimestamp);
        System.out.println("selectedDatetimeAsString:    "
                + selectedDatetimeAsString);
        System.out.println("selectedTimestampFromClient: "
                + selectedTimestampFromClient);
        System.out.println("selectedTimestampFromServer: "
                + selectedTimestampFromServer);
    }
}
