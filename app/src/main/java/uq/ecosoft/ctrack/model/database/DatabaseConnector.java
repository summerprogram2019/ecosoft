package uq.ecosoft.ctrack.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    /**
     * Database connector that manages the connection to the SQL database
     */
    private static String DB_URL = "jdbc:mysql://localhost:3306/ecosoft";
    private static String DB_USERNAME = "root";
    private static String DB_PASSWORD = "supercoolpassword";

    private Connection connection = null;

    /**
     * Get a connection to the database
     * This will establish new connections as needed
     * @return Connection to the database
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        // If already connected, return that
        if(connection != null) {
            return connection;
        }

        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return connection;
    }
}
