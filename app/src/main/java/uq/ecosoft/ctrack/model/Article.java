package uq.ecosoft.ctrack.model;


import android.annotation.SuppressLint;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.java.Log;

@Data @Log
public class Article {
    @NonNull
    Integer id;
    @NonNull
    String title;
    @NonNull
    String description;
    @NonNull
    String url;
    @NonNull
    Date time;
    @NonNull @ToString.Exclude @EqualsAndHashCode.Exclude
    Set<User> likes;

    /**
     * Adds the given user into the like Set.
     * @param bigFan the user to add into the like Set.
     */
    public void addUserLike(User bigFan) {
        log.info(String.format("Adding a like from User(%s) to %s"
                , bigFan.getDebugName(), this.getDebugName()));

        likes.add(bigFan);

        // TODO: Update DB
        // ArticleDatabase.likeArticle(bigFan.getId(), this.getId());
    }

    /**
     * Remove the given user from the like Set.
     * @param hater the user to remove from the like Set.
     */
    public void removeUserLike(User hater) {
        log.info(String.format("Removing a like by User(%s) for %s"
                , hater.getDebugName(), this.getDebugName()));

        likes.remove(hater);

        // TODO: Update DB
        // ArticleDatabase.unlikeArticle(hater.getId(), this.getId());
    }

    @SuppressLint("DefaultLocale")
    private String getDebugName() {
        return String.format("Article(%d, %s)", id, title);
    }
}
