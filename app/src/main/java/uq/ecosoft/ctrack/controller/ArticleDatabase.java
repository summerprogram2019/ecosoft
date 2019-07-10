package uq.ecosoft.ctrack.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import uq.ecosoft.ctrack.model.Article;

public class ArticleDatabase {
    /**
     * Add a new article into the database
     * @param title title of the article
     * @param description brief description of the article
     * @param url external URL to the article
     * @return whether or not the article was added successfully
     */
    public static Boolean addNewArticle(String title, String description, String url) throws SQLException {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("INSERT INTO articles (title, description, url, date) VALUES(?, ?, ?, ?)");
            q.setString(1, title);
            q.setString(2, description);
            q.setString(3, url);
            q.setTimestamp(4, currentTime);
            int rows = q.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    public static Boolean addNewArticle(Article article) {
        return false;
    }

    /**
     * Removes a given article from the database
     * @param articleID ID of the article to remove
     * @return whether or not the article was removed successfully
     */
    public static Boolean removeArticle(int articleID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            // Run Query
            con = DatabaseConnector.getConnection();

            q = con.prepareStatement("DELETE FROM articles WHERE id = ?");
            q.setInt(1, articleID);
            int rows = q.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    public static Boolean removeArticle(Article article) { return false; }

    /**
     * Retrieve the contents of a given article
     * @param articleID ID of the article
     */
    public static void getArticle(int articleID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            // Run Query
            // TODO
            throw new SQLException();
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    /**
     * Finds an article with a given title and returns the article ID
     * If multiple articles have the same title, this will <b>only</b> return the most recent one.
     * @param title Article title to search for
     * @return the ID of the article, if found
     * @throws SQLException
     */
    public static int findArticleWithTitle(String title) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;
        ResultSet rs = null;

        try {
            // Run Query
            con = DatabaseConnector.getConnection();
            q = con.prepareStatement("SELECT id FROM articles WHERE title = ? ORDER BY date DESC LIMIT 1");
            q.setString(1, title);
            rs = q.executeQuery();
            rs.first();
            return rs.getInt("id");
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    /**
     * Get a list of articles in the database, sorted by most recent
     * @param amount how many articles to retrieve
     */
    public static void getArticles(int amount) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            // Run Query
            // TODO
            throw new SQLException();
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    public static void getMostLikedArticles(int amount) throws SQLException {

    }

    /**
     * Get a total number of likes of the article
     * @param articleID articleID to get likes for
     * @return the number of likes for the given article
     */
    public static int getArticleLikes(int articleID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;
        ResultSet rs = null;

        try {
            // Run Query
            con = DatabaseConnector.getConnection();
            q = con.prepareStatement("SELECT COUNT(*) FROM liked_articles WHERE article = ?");
            q.setInt(1, articleID);

            rs = q.executeQuery();
            rs.first();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(rs, q, con);
        }
    }

    /**
     * Store that a user likes an article
     * @param userID
     * @param articleID
     */
    public static Boolean likeArticle(int userID, int articleID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            con = DatabaseConnector.getConnection();
            q = con.prepareStatement("INSERT INTO liked_articles (uid, article) VALUES(?, ?)");
            q.setInt(1, userID);
            q.setInt(2, articleID);
            int result = q.executeUpdate();
            return (result == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }

    /**
     * Remove a stored like from the article
     * @param userID
     * @param articleID
     * @return whether or not the like was successfully removed
     */
    public static Boolean unlikeArticle(int userID, int articleID) throws SQLException {
        // This allows us to close the connection at the end
        Connection con = null;
        PreparedStatement q = null;

        try {
            con = DatabaseConnector.getConnection();
            q = con.prepareStatement("DELETE FROM liked_articles WHERE uid=? AND article=?");
            q.setInt(1, userID);
            q.setInt(2, articleID);
            int result = q.executeUpdate();
            return (result == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            // Quietly close the connection
            DatabaseConnector.closeQuietly(q, con);
        }
    }
}
