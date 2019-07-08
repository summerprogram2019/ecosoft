package uq.ecosoft.ctrack.model;


import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.java.Log;

@Data @Log
public class Article {
    @NonNull
    String title;
    @NonNull
    String description;
    @NonNull
    String url;
    @NonNull
    Date time;
    @NonNull
    Set<User> likes;

    /**
     * Adds the given user into the like Set.
     * @param bigFan the user to add into the like Set.
     */
    public void addUserLike(User bigFan) {
        likes.add(bigFan);

        // TODO: Update DB
    }

    /**
     * Remove the given user from the like Set.
     * @param hater the user to remove from the like Set.
     */
    public void removeUserLike(User hater) {
        likes.remove(hater);

        // TODO: Update DB
    }
}
