package uq.ecosoft.ctrack.model.database;

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

    /**
     * Removes a given article from the database
     * @param articleID ID of the article to remove
     * @return whether or not the article was removed successfully
     */
    public Boolean removeArticle(int articleID) {
        return false;
    }

    /**
     * Retrieve the contents of a given article
     * @param articleID ID of the article
     */
    public void getArticle(int articleID) {

    }

    /**
     * Get a list of articles in the database, sorted by most recent
     * @param amount how many articles to retrieve
     */
    public void getArticles(int amount) {

    }

    public void getArticleLikes(int articleID) {

    }

    public void likeArticle(int userID, int articleID) {

    }
}
