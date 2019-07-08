package uq.ecosoft.ctrack;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import uq.ecosoft.ctrack.model.Garden;
import uq.ecosoft.ctrack.model.Goal;
import uq.ecosoft.ctrack.model.Score;
import uq.ecosoft.ctrack.model.Settings;
import uq.ecosoft.ctrack.model.User;
import uq.ecosoft.ctrack.model.activities.Activities;
import uq.ecosoft.ctrack.model.activities.Activity;
import uq.ecosoft.ctrack.model.activities.ActivityInstance;

import static org.junit.Assert.assertEquals;
import static uq.ecosoft.ctrack.model.activities.Activities.ACTIVITY_TYPE;


public class UserTests {
    @Test
    public void testFriendBehaviour() {
        User a = helpNewUser(0, "Harry");
        User b = helpNewUser(1, "Robert");

        a.sendFriendRequest(b);
        friendArrayCheck(a, 0, 0, b, 1, 0);
        a.removeFriendRequest(b);
        friendArrayCheck(a, 0, 0, b, 0, 0);
        a.sendFriendRequest(b);
        b.sendFriendRequest(a);
        friendArrayCheck(a, 0, 1, b, 0, 1);
        b.removeFriend(a);
        friendArrayCheck(a, 0, 0, b, 0, 0);
    }

    @Test
    public void testActivities() {
        User u = helpNewUser(0, "Sunny");

        ActivityInstance i1 = new ActivityInstance(ACTIVITY_TYPE.WALKING, new Date(), 100);
        ActivityInstance i2 = new ActivityInstance(ACTIVITY_TYPE.DRIVING, new Date(), 10);
        ActivityInstance i3 = new ActivityInstance(ACTIVITY_TYPE.WALKING, new Date(), 300);

        assertEquals(new Integer(0), u.calculateTotalScore());
        assertEquals(new Integer(0), u.calculateActivityScore(ACTIVITY_TYPE.WALKING));
        assertEquals(new Integer(0), u.calculateActivityScore(ACTIVITY_TYPE.DRIVING));
        u.getActivities().add(i1);
        assertEquals(new Integer(1000), u.calculateTotalScore());
        assertEquals(new Integer(1000), u.calculateActivityScore(ACTIVITY_TYPE.WALKING));
        assertEquals(new Integer(0), u.calculateActivityScore(ACTIVITY_TYPE.DRIVING));
        u.getActivities().add(i2);
        assertEquals(new Integer(0), u.calculateTotalScore());
        assertEquals(new Integer(1000), u.calculateActivityScore(ACTIVITY_TYPE.WALKING));
        assertEquals(new Integer(-1000), u.calculateActivityScore(ACTIVITY_TYPE.DRIVING));
        u.getActivities().add(i3);
        assertEquals(new Integer(3000), u.calculateTotalScore());
        assertEquals(new Integer(4000), u.calculateActivityScore(ACTIVITY_TYPE.WALKING));
        assertEquals(new Integer(-1000), u.calculateActivityScore(ACTIVITY_TYPE.DRIVING));
    }

    private User helpNewUser(Integer id, String name) {
        return new User(id, "foo", name, new Score(),
                new ArrayList<Goal>(), new Garden(), new HashSet<User>(), new HashSet<User>(), new Settings(),
                new ArrayList<ActivityInstance>());
    }

    private void friendArrayCheck(User a, Integer aRequests, Integer aFriends,
                                  User b, Integer bRequests, Integer bFriends) {
        assertEquals(aRequests.intValue(), a.getFriendRequests().size());
        assertEquals(aFriends.intValue(), a.getFriends().size());
        assertEquals(bRequests.intValue(), b.getFriendRequests().size());
        assertEquals(bFriends.intValue(), b.getFriends().size());
    }
}
