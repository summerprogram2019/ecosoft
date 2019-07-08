package uq.ecosoft.ctrack.model.database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private DatabaseConnector connector;
    private static String secretSeed = "antelope";

    /**
     * Create a UserDatabase class specifying a DatabaseConnector
     * This allows a shared connection between DB subclasses
     * @param connector the connector for managing connections
     */
    public UserDatabase(DatabaseConnector connector) {
        this.connector = connector;
    }

    /**
     * Get a password given a username
     * Passwords are securely stored - this will return a salted hash
     * @param username name of the user to get password from
     * @return hashed password of the user
     * @throws SQLException
     */
    public String getPasswordFromUsername(String username) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("SELECT password FROM user WHERE username = ?");
        q.setString(1, username);
        ResultSet rs = q.executeQuery();

        // Read the password from the result
        rs.first();
        return rs.getString("password");
    }

    /**
     * Get the userID given a username
     * UserID is used as the foreign key throughout the database
     * @param username name of the user to get ID from
     * @return the userID of the user
     * @throws SQLException
     */
    public int getUserIDFromUsername(String username) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("SELECT uid FROM user WHERE username = ?");
        q.setString(1, username);
        ResultSet rs = q.executeQuery();

        // Read the userID from the result
        rs.first();
        return rs.getInt("uid");
    }

    /**
     * Generates a user token for a given username
     * This uses the username + a super secret server seed to verify identities
     * Very useful for authentication purposes
     * @param username the user to generate a token for
     * @return an SHA-256 hashed user token
     */
    public void generateUserToken(String username) {
        // removed due to API restrictions
    }

    /**
     * Get a map of the settings applied by a user
     * Only certain privacy-related settings are tracked in this
     * @param userID which user to collect settings from
     * @return a map of setting names and a boolean state
     * @throws SQLException
     */
    public Map<String,Boolean> getUserSettings(int userID) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("SELECT setting, state FROM settings WHERE uid = ?");
        q.setInt(1, userID);
        ResultSet rs = q.executeQuery();

        Map<String,Boolean> settings = new HashMap<>();
        while(rs.next()) {
            settings.put(rs.getString("setting"), rs.getBoolean("state"));
        }
        return settings;
    }

    /**
     * Removes a given user from the database
     * @param userID the user to be removed
     * @return whether or not the user was sucessfully deleted
     * @throws SQLException
     */
    public boolean removeUser(int userID) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("DELETE FROM user WHERE uid = ?");
        q.setInt(1, userID);
        int result = q.executeUpdate();
        return (result == 1);
    }

    /**
     * Register an account into the database
     * @param username the username for the new account
     * @param password securely hashed password. See the PasswordManager class
     * @return whether or not the user was sucessfully registered
     * @throws SQLException
     */
    public Boolean addNewUser(String username, String password) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("INSERT INTO user (username, password) VALUES(?, ?)");
        q.setString(1, username);
        q.setString(2, password);
        int rows = q.executeUpdate();
        return (rows == 1);
    }

    public void getFriends(int userID) throws SQLException {

    }

    public void addFriend(int userID, int friendID) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("INSERT INTO friends (user1, user2) VALUES(?, ?");
        q.setInt(1, userID);
        q.setInt(2, friendID);
    }
}
