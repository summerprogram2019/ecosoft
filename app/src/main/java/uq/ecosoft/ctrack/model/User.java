package uq.ecosoft.ctrack.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.java.Log;
import uq.ecosoft.ctrack.model.activities.Activities;
import uq.ecosoft.ctrack.model.activities.ActivityInstance;

@Data
@Log
public class User {
    @NonNull Integer id;
    @NonNull String password;
    @NonNull String realName;

    @NonNull Score score;
    @NonNull Garden garden;
    @NonNull Settings settings;

    @NonNull @ToString.Exclude @EqualsAndHashCode.Exclude List<Goal> goals;
    @NonNull @ToString.Exclude @EqualsAndHashCode.Exclude List<ActivityInstance> activities;
    @NonNull @ToString.Exclude @EqualsAndHashCode.Exclude Set<User> friendRequests;
    @NonNull @ToString.Exclude @EqualsAndHashCode.Exclude Set<User> friends;


    /**
     * This method will send a friend request to another user. If the user has already
     * sent us a friend request, we will accept it using {@link #acceptFriendRequest(User)}.
     * Finally we will update the database with the new friend request.
     *
     * @param newFriend The person to add as a friend or accept the friend request from
     */
    public void sendFriendRequest(@NonNull User newFriend) {
        log.info("Sending a friend request to " + newFriend.getDebugName() + " from " +
                getDebugName());
        // Check if our new friend has sent us a friend request
        if (getFriendRequests().contains(newFriend)) {
            log.info("Other user has already sent us a friend request");
            // Add them to our friends list
            newFriend.getFriendRequests().add(this);

            // and accept the friend request
            acceptFriendRequest(newFriend);

            // TODO: DB
        } else {
            log.info("Other user has not sent us a friend request, sending them one");
            // Add ourselves to the other persons friend requests
            newFriend.getFriendRequests().add(this);

            // TODO: Update the db
        }
    }

    /**
     * Accept friend requests checks if the user has a friend request from a person (or is already
     * friends with this person), if this is true it will accept the request. Will also trigger
     * accepting for the second user if propagate required.
     *
     * @param newFriend the other user to be in the friendship
     * @param propagate if both users have requests with each other, then it will also add
     *                  to their friends list
     */
    public void acceptFriendRequest(@NonNull User newFriend, @NonNull Boolean propagate) {
        log.info("Accepting friend request from " + newFriend.getDebugName() + "for"
                + getDebugName() + " with propagate = " + propagate);
        // Ensures for both users have the other as a friend or have a request
        if ((friendRequests.contains(newFriend) || friends.contains(newFriend)) &&
                ((newFriend.getFriendRequests().contains(this))) ||
                (newFriend.getFriends().contains(this))) {
            removeFriendRequest(newFriend);
            friends.add(newFriend);
            if (propagate) {
                newFriend.acceptFriendRequest(this, false);
            }
            // TODO: Update the db
        } else {
            log.warning("Attempted to add a friend not in the friend request list " +
                    newFriend.getDebugName());
        }
    }

    /**
     * This method overloads with {@link #acceptFriendRequest(User, Boolean)}, but specifies
     * the second parameter to be true.
     *
     * @param newFriend the friend to add and propagate too.
     * @see User#acceptFriendRequest(User, Boolean)
     */
    public void acceptFriendRequest(@NonNull User newFriend) {
        acceptFriendRequest(newFriend, true);
    }

    /**
     * Removes a current friend after checking if they are currently a friend. If propagation is
     * on it will also remove the current user from the other users friends list.
     *
     * @param newEnemy  user to check and remove from our friends list
     * @param propagate if true will also attempt to remove the current user from the other person
     */
    public void removeFriend(@NonNull User newEnemy, @NonNull Boolean propagate) {
        log.info("Removing friend " + newEnemy.getDebugName() + " from " + getDebugName() +
                " with propagate = " + propagate);
        // it currently warns that the check is unnecessary, it is not TODO: remove this comment
        if (getFriends().contains(newEnemy)) {
            getFriends().remove(newEnemy);

            // TODO: Update DB
        } else {
            log.warning("Attempted to remove friend but they are not currently a friend!");
        }
        if (propagate) {
            newEnemy.removeFriend(this, false);
        }
    }

    /**
     * This method overloads with {@link #removeFriend(User, Boolean)}, but specifies
     * the second parameter to be true.
     *
     * @param newEnemy the friend to remove and propagate too.
     * @see User#removeFriend(User, Boolean)
     */
    public void removeFriend(@NonNull User newEnemy) {
        removeFriend(newEnemy, true);
    }

    /**
     * Removes the specified user from the other persons friends list
     *
     * @param newEnemy person to remove from our friends requests list if present
     */
    public void removeFriendRequest(@NonNull User newEnemy) {
        log.info("Removing friend " + newEnemy.getDebugName() + " from " + getDebugName());
        if (newEnemy.getFriendRequests().contains(this)) {
            newEnemy.getFriendRequests().remove(this);

            // TODO: Remove from db
        }

    }

    /**
     * Calculates and adds the scores of each activity instance
     *
     * @return the total of all the scores
     */
    public Integer calculateTotalScore() {
        Integer result = 0;
        for (ActivityInstance a : activities) {
            result += a.getScore();
        }
        return result;
    }

    /**
     * Calculates and adds the scores of each activity instance of the specified type
     *
     * @param actType the type of activity to total
     * @return the total of all the scores matching {@param actType}
     */
    public Integer calculateActivityScore(Activities.ACTIVITY_TYPE actType) {
        Integer result = 0;
        for (ActivityInstance a : activities) {
            if (a.getActivity().equals(actType)) {
                result += a.getScore();
            }
        }
        return result;
    }

    /**
     * Provides a convenience method for getting a useful log name
     *
     * @return returns '(uid) name' of the user
     */
    private String getDebugName() {
        return "(" + this.getId() + ") " + this.getRealName();
    }

}
