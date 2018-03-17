package net.avax.cj.jdbc.tztest;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Boolean useLegacyDatetimeCode = false;

        String url = "jdbc:mysql://localhost/tztest?user=tztest"
                + "&password=insecure&useSSL=false";

        if (useLegacyDatetimeCode != null) {
            url += "&useLegacyDatetimeCode=" + useLegacyDatetimeCode;
        }

        Connection conn = DriverManager.getConnection(url);

        // Create Oracle DatabaseMetaData object
        DatabaseMetaData meta = conn.getMetaData();

        // gets driver info:
        System.out.println("jdbcDriverVersion:                   "
                + meta.getDriverVersion());
        System.out.println("useLegacyDatetimeCode:               "
                + useLegacyDatetimeCode);

        // DROP TABLE IF EXISTS tztest;
        // CREATE TABLE tztest (id INT(11) NOT NULL AUTO_INCREMENT, test_date
        // DATE NOT NULL, test_datetime DATETIME(6) NOT NULL, test_timestamp
        // TIMESTAMP DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id));

        long insertedTime = System.currentTimeMillis();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO tztest"
                        + " (test_date, test_datetime, test_timestamp) VALUES"
                        + " (?, ?, NULL)",
                Statement.RETURN_GENERATED_KEYS);

        String insertedDateAsString = new Date(insertedTime).toString();
        String insertedDatetimeAsString = new Timestamp(insertedTime)
                .toString();

        ps.setString(1, insertedDateAsString);
        ps.setString(2, insertedDatetimeAsString);

        int rowCount = ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rowCount != 1 || !rs.next()) {
            throw new AssertionError("Failed to insert row");
        }

        int insertedRowId = rs.getInt(1);

        System.out.println();
        System.out.println("insertedRowId:                       "
                + insertedRowId);
        System.out.println("insertedDateAsString:                "
                + insertedDateAsString);
        System.out.println("insertedDatetimeAsString:            "
                + insertedDatetimeAsString);
        System.out.println("insertedTimestamp:                   NULL");

        ps = conn.prepareStatement("SELECT test_date, test_datetime,"
                + " test_timestamp FROM tztest WHERE id = ?");

        ps.setInt(1, insertedRowId);

        if (!ps.execute() || !(rs = ps.getResultSet()).next()) {
            throw new AssertionError("Failed to select inserted row");
        }

        String selectedInsertedDateAsString = rs.getString(1);
        String selectedInsertedDatetimeAsString = rs.getString(2);
        String selectedInsertedTimestampAsString = rs.getString(3);

        System.out.println();
        System.out.println("selectedInsertedDateAsString:        "
                + selectedInsertedDateAsString);
        System.out.println("selectedInsertedDatetimeAsString:    "
                + selectedInsertedDatetimeAsString);
        System.out.println("selectedInsertedTimestampAsString:   "
                + selectedInsertedTimestampAsString);

        long updatedTime = System.currentTimeMillis();

        Date updatedDateAsDate = new Date(updatedTime);
        Timestamp updatedDatetimeAsTimestamp = new Timestamp(updatedTime);
        Timestamp updatedTimestampAsTimestamp = new Timestamp(updatedTime);

        ps = conn.prepareStatement("UPDATE tztest SET test_date = ?,"
                + " test_datetime = ?, test_timestamp = ? WHERE id = ?");

        ps.setDate(1, updatedDateAsDate);
        ps.setTimestamp(2, updatedDatetimeAsTimestamp);
        ps.setTimestamp(3, updatedTimestampAsTimestamp);
        ps.setInt(4, insertedRowId);

        rowCount = ps.executeUpdate();

        if (rowCount != 1) {
            throw new AssertionError("Failed to update row");
        }

        System.out.println();
        System.out.println("updatedDateAsDate:                   "
                + updatedDateAsDate);
        System.out.println("updatedDatetimeAsTimestamp:          "
                + updatedDatetimeAsTimestamp);
        System.out.println("updatedTimestampAsTimestamp:         "
                + updatedTimestampAsTimestamp);

        ps = conn.prepareStatement("SELECT test_date, test_datetime,"
                + " test_timestamp FROM tztest WHERE id = ?");

        ps.setInt(1, insertedRowId);

        if (!ps.execute() || !(rs = ps.getResultSet()).next()) {
            throw new AssertionError("Failed to select updated row");
        }

        Date selectedUpdatedDateAsDate = rs.getDate(1);
        Timestamp selectedUpdatedDatetimeAsTimestamp = rs.getTimestamp(2);
        Timestamp selectedUpdatedTimestampAsTimestamp = rs.getTimestamp(3);

        System.out.println();
        System.out.println("selectedUpdatedDateAsDate:           "
                + selectedUpdatedDateAsDate);
        System.out.println("selectedUpdatedDatetimeAsTimestamp:  "
                + selectedUpdatedDatetimeAsTimestamp);
        System.out.println("selectedUpdatedTimestampAsTimestamp: "
                + selectedUpdatedTimestampAsTimestamp);
    }
}
