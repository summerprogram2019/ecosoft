package uq.ecosoft.ctrack.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

public class DatabaseConnector {
    /**
     * Database connector that manages the connection to the SQL database
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "supercoolpassword";
    private static final String DB_DEFAULT_DATABASE = "ecosoft";

    @Getter @Setter
    private static String currentDatabase = "ecosoft";

    /**
     * Get a connection to the database
     * This will establish new connections as needed
     * @return Connection to the database
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL + currentDatabase, DB_USERNAME, DB_PASSWORD);
    }

    /**
     * Probably a disaster function - be careful
     * Resets the database to the default one in settings
     */
    public static void resetDatabase() {
        setCurrentDatabase(DB_DEFAULT_DATABASE);
    }

    /**
     * Helper function for closing a database connection when done with it
     * This takes in the ResultSet, PreparedStatement, and Connection
     * This quietly ignores errors that are thrown
     * @param rs ResultSet to close
     * @param ps PreparedStatement to close
     * @param con Connection to close
     */
    public static void closeQuietly(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            // ignore
        } catch (NullPointerException e) {
            // ignore
        }
    }

    /**
     * Helper function for closing a database connection when done with it
     * This takes in the PreparedStatement and Connection
     * This quietly ignores errors that are thrown
     * @param ps PreparedStatement to close
     * @param con Connection to close
     */
    public static void closeQuietly(PreparedStatement ps, Connection con) {
        try {
            ps.close();
            con.close();
        } catch (SQLException e) {
            // ignore
        }
    }

    /**
     * Helper function for closing a database connection when done with it
     * This takes in only the Connection itself
     * This quietly ignores errors that are thrown
     * @param con Connection to close
     */
    public static void closeQuietly(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            // ignore
        }
    }
}
