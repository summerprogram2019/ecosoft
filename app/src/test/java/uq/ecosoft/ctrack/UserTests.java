package uq.ecosoft.ctrack;

import org.junit.Test;

import java.util.Date;

import uq.ecosoft.ctrack.model.User;
import uq.ecosoft.ctrack.model.activities.ActivityInstance;
import uq.ecosoft.ctrack.model.activities.Activity;

import static org.junit.Assert.assertEquals;


public class UserTests {
    @Test
    public void testFriendBehaviour() {
        User a = TestUtil.createNewUser(0, "Harry");
        User b = TestUtil.createNewUser(1, "Robert");

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

    /*
    @Test
    public void testActivities() {
        User u = TestUtil.createNewUser(0, "Sunny");

        ActivityInstance i1 = new ActivityInstance(Activity.WALKING, new Date(), 100);
        ActivityInstance i2 = new ActivityInstance(Activity.DRIVING, new Date(), 10);
        ActivityInstance i3 = new ActivityInstance(Activity.WALKING, new Date(), 300);

        assertEquals(new Integer(0), u.calculateTotalScore());
        assertEquals(new Integer(0), u.calculateActivityScore(Activity.WALKING));
        assertEquals(new Integer(0), u.calculateActivityScore(Activity.DRIVING));
        u.getActivities().add(i1);
        assertEquals(new Integer(1000), u.calculateTotalScore());
        assertEquals(new Integer(1000), u.calculateActivityScore(Activity.WALKING));
        assertEquals(new Integer(0), u.calculateActivityScore(Activity.DRIVING));
        u.getActivities().add(i2);
        assertEquals(new Integer(0), u.calculateTotalScore());
        assertEquals(new Integer(1000), u.calculateActivityScore(Activity.WALKING));
        assertEquals(new Integer(-1000), u.calculateActivityScore(Activity.DRIVING));
        u.getActivities().add(i3);
        assertEquals(new Integer(3000), u.calculateTotalScore());
        assertEquals(new Integer(4000), u.calculateActivityScore(Activity.WALKING));
        assertEquals(new Integer(-1000), u.calculateActivityScore(Activity.DRIVING));
    }
    */

    private void friendArrayCheck(User a, Integer aRequests, Integer aFriends,
                                  User b, Integer bRequests, Integer bFriends) {
        assertEquals(aRequests.intValue(), a.getFriendRequests().size());
        assertEquals(aFriends.intValue(), a.getFriends().size());
        assertEquals(bRequests.intValue(), b.getFriendRequests().size());
        assertEquals(bFriends.intValue(), b.getFriends().size());
    }
}
