package uq.ecosoft.ctrack.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import uq.ecosoft.ctrack.model.User;
import uq.ecosoft.ctrack.model.activities.ActivityInstance;
import uq.ecosoft.ctrack.model.garden.PlantInstance;

public class UserDatabase {
    private static String secretSeed = "antelope";
    /**
     * Get a password given a username
     * Passwords are securely stored - this will return a salted hash
     * @param username name of the user to get password from
     * @return hashed password of the user
     * @throws SQLException
     */
    public static String getPasswordFromUsername(String username) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;
        ResultSet rs = null;

        try {
            // Run the query
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("SELECT password FROM user WHERE username = ?");
            q.setString(1, username);
            rs = q.executeQuery();

            // Read the password from the result
            rs.first();
            return rs.getString("password");
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(rs, q, con);
        }
    }

    /**
     * Get the userID given a username
     * UserID is used as the foreign key throughout the database
     * @param username name of the user to get ID from
     * @return the userID of the user
     * @throws SQLException
     */
    public static int getUserIDFromUsername(String username) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;
        ResultSet rs = null;

        try {
            // Run the query
            con = DatabaseConnector.getConnection();
            q = con.prepareStatement("SELECT uid FROM user WHERE username = ?");
            q.setString(1, username);
            rs = q.executeQuery();

            // Read the userID from the result
            rs.first();
            return rs.getInt("uid");
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(rs, q, con);
        }
    }

    /**
     * Returns a user object given an ID
     * This function is not complete - need User constructor first?
     * @param id the userID to fetch a User object for
     * @return User object
     */
    public static User getUserFromID(int id) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("SELECT * FROM user WHERE uid = ?");
            q.setInt(1, id);
            rs = q.executeQuery();

            rs.first();
            String username = rs.getString("username");
            String password = rs.getString("password");
            return new User(id, username, password, username, new ArrayList<PlantInstance>() ,
                    new ArrayList<ActivityInstance>(), new HashSet<User>(), new HashSet<User>());
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(rs, q, con);
        }
    }

    /**
     * Generates a user token for a given username
     * This uses the username + a super secret server seed to verify identities
     * Very useful for authentication purposes
     * @param username the user to generate a token for
     * @return an SHA-256 hashed user token
     */
    public static void generateUserToken(String username) {
        // removed due to API restrictions
    }

    /**
     * Get a map of the settings applied by a user
     * Only certain privacy-related settings are tracked in this
     * @param userID which user to collect settings from
     * @return a map of setting names and a boolean state
     * @throws SQLException
     */
    public static Map<String,Boolean> getUserSettings(int userID) throws SQLException {
        Connection con = DatabaseConnector.getConnection();

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
    public static boolean removeUser(int userID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("DELETE FROM user WHERE uid = ?");
            q.setInt(1, userID);
            int result = q.executeUpdate();
            return (result == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    public static boolean removeUser(User user) throws SQLException {
        return removeUser(user.getId());
    }

    /**
     * Register an account into the database
     * @param username the username for the new account
     * @param password securely hashed password. See the PasswordManager class
     * @return whether or not the user was sucessfully registered
     * @throws SQLException
     */
    public static Boolean addNewUser(String username, String password) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("INSERT INTO user (username, password) VALUES(?, ?)");
            q.setString(1, username);
            q.setString(2, password);
            int rows = q.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    /**
     * Register an account into the database
     * @param user the user object to create in the database
     * @return whether or not the user was successfully registered
     * @throws SQLException
     */
    public static Boolean addNewUser(User user) throws SQLException {
        return addNewUser(user.getUsername(), user.getPassword());
    }

    /**
     *
     * @param userID
     * @throws SQLException
     */
    public static void getFriends(int userID) throws SQLException {

    }

    /**
     * Add a friend or send a friend request to the database
     * @param userID Original user
     * @param friendID Friend to remove
     * @return whether the removal was successful
     * @throws SQLException
     */
    public static Boolean addFriend(int userID, int friendID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("INSERT INTO friends (user1, user2) VALUES(?, ?)");
            q.setInt(1, userID);
            q.setInt(2, friendID);
            int rows = q.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    /**
     * Remove a friend or friend request from the database
     * @param userID Original user
     * @param friendID Friend to remove
     * @return whether the removal was successful
     * @throws SQLException
     */
    public static Boolean removeFriend(int userID, int friendID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("DELETE FROM friends WHERE user1 = ? AND user2 = ?");
            q.setInt(1, userID);
            q.setInt(2, friendID);
            int rows = q.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }
}