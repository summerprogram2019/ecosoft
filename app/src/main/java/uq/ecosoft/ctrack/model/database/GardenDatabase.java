package uq.ecosoft.ctrack.model.database;

public class GardenDatabase {
    private DatabaseConnector connector;

    /**
     * Create a GardenDatabase class specifying a DatabaseConnector
     * This allows a shared connection between DB subclasses
     * @param connector the connector for managing connections
     */
    public GardenDatabase(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void getFlowers() {

    }

    public void getUserGarden(int userID) {

    }

    public void plantFlower(int userID, int flowerID) {

    }
}