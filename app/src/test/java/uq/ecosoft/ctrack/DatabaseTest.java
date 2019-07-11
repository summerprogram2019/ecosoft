package uq.ecosoft.ctrack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import uq.ecosoft.ctrack.controller.ArticleDatabase;
import uq.ecosoft.ctrack.controller.DatabaseConnector;
import uq.ecosoft.ctrack.controller.PasswordHelper;
import uq.ecosoft.ctrack.controller.UserDatabase;

import static org.junit.Assert.*;

public class DatabaseTest {
    @BeforeClass
    public static void beforeClass() {
        //TODO: Create and changeover to a new database
        DatabaseConnector.setCurrentDatabase("ecosoft_test");
    }

    @AfterClass
    public static void afterClass() {
        //TODO: Scrap the testing database
        DatabaseConnector.resetDatabase();
    }

    @Before
    public void setup() {
        // Intentionally left blank
    }

    @After
    public void teardown() {
        // Intentionally left blank
    }

    @Test
    public void testDatabaseConnection() throws SQLException {
        Connection con = DatabaseConnector.getConnection();
        assertTrue(con instanceof Connection);
        DatabaseConnector.closeQuietly(con);
    }

    @Test
    public void testUser() throws SQLException {
        UserDatabase.addNewUser("coolusername", "securedpassword");
        int uid = UserDatabase.getUserIDFromUsername("coolusername");
        boolean success = UserDatabase.removeUser(uid);
        assertTrue(success);
    }

    @Test
    public void testDatabaseFriendship() throws SQLException {
        UserDatabase.addNewUser("cool1", "securedpassword");
        UserDatabase.addNewUser("cool2", "securedpassword");
        int uid1 = UserDatabase.getUserIDFromUsername("cool1");
        int uid2 = UserDatabase.getUserIDFromUsername("cool2");
        UserDatabase.addFriend(uid1, uid2);
        UserDatabase.addFriend(uid2, uid1);
        UserDatabase.removeFriend(uid1, uid2);
        UserDatabase.removeFriend(uid2, uid1);
        UserDatabase.removeUser(uid1);
        UserDatabase.removeUser(uid2);
    }

    @Test
    public void testArticle() throws SQLException {
        ArticleDatabase.addNewArticle("Database Test", "AAAAAAAAAAA", "test.com");
        int id = ArticleDatabase.findArticleWithTitle("Database Test");
        ArticleDatabase.removeArticle(id);
    }

    @Test
    public void testArticleLikes() throws SQLException {
        ArticleDatabase.addNewArticle("Database Like Test", "AAAHAHAHA", "test.com");
        int id = ArticleDatabase.findArticleWithTitle("Database Like Test");

        UserDatabase.addNewUser("reader1", "securedpassword");
        UserDatabase.addNewUser("reader2", "securedpassword");
        int uid1 = UserDatabase.getUserIDFromUsername("reader1");
        int uid2 = UserDatabase.getUserIDFromUsername("reader2");

        assertEquals(0, ArticleDatabase.getArticleLikes(id));
        ArticleDatabase.likeArticle(uid1, id);
        ArticleDatabase.likeArticle(uid2, id);
        assertEquals(2, ArticleDatabase.getArticleLikes(id));
        ArticleDatabase.unlikeArticle(uid2, id);
        assertEquals(1, ArticleDatabase.getArticleLikes(id));

        UserDatabase.removeUser(uid1);
        UserDatabase.removeUser(uid2);
        ArticleDatabase.removeArticle(id);
    }

    @Test
    public void testPasswordSecurity() throws SQLException {
        String pw1 = PasswordHelper.computeHash("password");
        String pw2 = PasswordHelper.computeHashWithSalt("password", "cooluser2");
        String pw3 = PasswordHelper.computeHashSecretSalt("password", "cooluser3");
        assertEquals(pw1, "2e2b24f8ee40bb847fe85bb23336a39ef5948e6b49d897419ced68766b16967a");
        assertEquals(pw2, "731e22dc27e1cdd461623ebd6ea6af7ee874fac8989cdb028a000c4da276f134");
        assertEquals(pw3, "39199e31f4bd7d68bb9bc183d55a6c7a6494e1b1c64ce9b2a0a35aff8627894e");
    }
}
