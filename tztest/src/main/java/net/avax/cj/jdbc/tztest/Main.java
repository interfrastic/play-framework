package net.avax.cj.jdbc.tztest;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tztest"
                    + "?user=release&password=release"
                    + "&useSSL=false&serverTimezone=America/Los_Angeles");

            // Create Oracle DatabaseMetaData object
            DatabaseMetaData meta = conn.getMetaData();

            // gets driver info:
            System.out.println("JDBC driver version is "
                    + meta.getDriverVersion());

            long now = System.currentTimeMillis();

            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                ps = conn.prepareStatement("INSERT INTO tztest"
                                + " (test_date, test_datetime) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                ps.setDate(1, new Date(now));
                ps.setTimestamp(2, new Timestamp(now));

                int rowCount = ps.executeUpdate();

                rs = ps.getGeneratedKeys();

                if (rowCount == 1 && rs.next()) {
                    int id  = rs.getInt(1);

                    System.out.println("Inserted row with ID " +id);
                } else {
                    System.out.println("Something went wrong!");
                }
            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } finally {
                // it is a good idea to release
                // resources in a finally{} block
                // in reverse-order of their creation
                // if they are no-longer needed

                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    } // ignore

                    rs = null;
                }

                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException sqlEx) {
                    } // ignore

                    ps = null;
                }
            }

            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT test_date FROM tztest");

                // Now do something with the ResultSet ....
            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } finally {
                // it is a good idea to release
                // resources in a finally{} block
                // in reverse-order of their creation
                // if they are no-longer needed

                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    } // ignore

                    rs = null;
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) {
                    } // ignore

                    stmt = null;
                }
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
