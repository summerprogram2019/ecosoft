package uq.ecosoft.ctrack.model.database;

public class TrackingDatabase {
    private DatabaseConnector connector;

    /**
     * Create a TrackingDatabase class specifying a DatabaseConnector
     * This allows a shared connection between DB subclasses
     * @param connector the connector for managing connections
     */
    public TrackingDatabase(DatabaseConnector connector) {
        this.connector = connector;
    }

    /**
     * Get a list of activities that the user has performed today
     * @param userID the user to query
     */
    public void getDailyActivities(int userID) {

    }

    /**
     * Get a list of activities that the user has performed since registering
     * @param userID
     */
    public void getFullActivities(int userID) {

    }

    /**
     * Get a total overall score for this user
     * This is calculated in the query
     * @param userID
     */
    public void getScore(int userID) {

    }

    /**
     * Get a friends leaderboard ranking for this user
     * This will select all of the friends and then generate a scoreboard
     * @param userID
     */
    public void getFriendsLeaderboard(int userID) {

    }

    /**
     * Get a list of goals that this user has set
     * @param userID
     */
    public void getGoals(int userID) {

    }

    /**
     * Get a list of unfinished goals that this user has set
     */
    public void getUnfinishedGoals(int userID) {

    }

    public void performActivity(int userID, int activityID, int amount) {

    }

    public void setGoal(int userID, String description) {

    }
}
