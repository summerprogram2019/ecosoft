package uq.ecosoft.ctrack;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import uq.ecosoft.ctrack.model.Article;
import uq.ecosoft.ctrack.model.User;
import uq.ecosoft.ctrack.model.activities.ActivityInstance;
import uq.ecosoft.ctrack.model.garden.PlantInstance;

public abstract class TestUtil {
    private static Random r = new Random();

    /**
     * Helper function to create a new user; does not check for unique ID and unique username!
     * @param id The ID for the new user
     * @param username The username for the ner user
     * @return a newly created User instance
     */
    public static User createNewUser(Integer id, String username) {
        return new User(id, username, "hunter2", username, new ArrayList<PlantInstance>(),
                new ArrayList<ActivityInstance>(), new HashSet<User>(), new HashSet<User>());
    }

    /**
     * Helper function to create a new article; does not check for unique ID!
     * @param id The ID for the new article
     * @param title The title for the new article
     * @return a newly created Article instance
     */
    public static Article createNewArticle(Integer id, String title) {
        String[] arr = {"com", "org", "ru", "net", "ly"};
        String s = arr[r.nextInt(arr.length)];
        return new Article(id, title, "reduce, reuse, recycle"
                , String.format("https://www.%s.%s"
                , title.replaceAll(" ", "-"), s)
                , new Date(), new HashSet<User>());
    }
}
