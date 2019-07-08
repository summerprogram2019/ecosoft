package uq.ecosoft.ctrack.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uq.ecosoft.ctrack.model.Article;

public class ArticleDatabase {
    private DatabaseConnector connector;

    /**
     * Create a ArticleDatabase class specifying a DatabaseConnector
     * This allows a shared connection between DB subclasses
     * @param connector the connector for managing connections
     */
    public ArticleDatabase(DatabaseConnector connector) {
        this.connector = connector;
    }

    /**
     * Add a new article into the database
     * @param title title of the article
     * @param description brief description of the article
     * @param url external URL to the article
     * @return whether or not the article was added successfully
     */
    public Boolean addNewArticle(String title, String description, String url) {
        return false;
    }

    public Boolean addNewArticle(Article article) {
        return false;
    }

    /**
     * Removes a given article from the database
     * @param articleID ID of the article to remove
     * @return whether or not the article was removed successfully
     */
    public Boolean removeArticle(int articleID) {
        return false;
    }

    public Boolean removeArticle(Article article) { return false; }

    /**
     * Retrieve the contents of a given article
     * @param articleID ID of the article
     */
    public void getArticle(int articleID) {
        // return new Article();
    }

    /**
     * Get a list of articles in the database, sorted by most recent
     * @param amount how many articles to retrieve
     */
    public void getArticles(int amount) {

    }

    /**
     * Get a total number of likes of the article
     * @param articleID
     */
    public void getArticleLikes(int articleID) {

    }

    /**
     * Store that a user likes an article
     * @param userID
     * @param articleID
     */
    public Boolean likeArticle(int userID, int articleID) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("INSERT INTO liked_articles (uid, article) VALUES(?, ?)");
        q.setInt(1, userID);
        q.setInt(2, articleID);
        int result = q.executeUpdate();
        return (result == 1);
    }

    /**
     * Remove a stored like from the article
     * @param userID
     * @param articleID
     * @return whether or not the like was successfully removed
     */
    public Boolean unlikeArticle(int userID, int articleID) throws SQLException {
        Connection con = connector.getConnection();

        PreparedStatement q = con.prepareStatement("DELETE FROM liked_articles WHERE uid=? AND article=?");
        q.setInt(1, userID);
        q.setInt(2, articleID);
        int result = q.executeUpdate();
        return (result == 1);
    }
}
