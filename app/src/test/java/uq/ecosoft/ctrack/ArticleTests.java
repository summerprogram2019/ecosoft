package uq.ecosoft.ctrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uq.ecosoft.ctrack.model.Article;
import uq.ecosoft.ctrack.model.User;

import static org.junit.Assert.assertTrue;

public class ArticleTests {
    @Test
    public void testZeroUserLikeArticle() {
        Article art = TestUtil.createNewArticle(0, "carbon emission");

        articleLikeSetCheck(art, new ArrayList<User>());
    }

    @Test
    public void testOneUserLikeArticle() {
        User a = TestUtil.createNewUser(0, "Harry");
        Article art = TestUtil.createNewArticle(0, "carbon emission");

        art.addUserLike(a);
        articleLikeSetCheck(art, Arrays.asList(a));
    }

    @Test
    public void testOneUserMultiLikeArticle() {
        User a = TestUtil.createNewUser(0, "Harry");
        Article art = TestUtil.createNewArticle(0, "carbon emission");

        art.addUserLike(a);
        art.addUserLike(a);

        articleLikeSetCheck(art, Arrays.asList(a));
    }

    @Test
    public void testMultiUserLikeArticle() {
        User a = TestUtil.createNewUser(0, "Harry");
        User b = TestUtil.createNewUser(1, "Robert");
        Article art = TestUtil.createNewArticle(0, "carbon emission");

        art.addUserLike(a);
        art.addUserLike(b);

        articleLikeSetCheck(art, Arrays.asList(a, b));
    }

    @Test
    public void testFanUnlikeArticle() {
        User a = TestUtil.createNewUser(0, "Harry");
        Article art = TestUtil.createNewArticle(0, "carbon emission");

        art.addUserLike(a);
        art.removeUserLike(a);

        articleLikeSetCheck(art, new ArrayList<User>());
    }

    @Test
    public void testHaterUnlikeArticle() {
        User a = TestUtil.createNewUser(0, "Harry");
        Article art = TestUtil.createNewArticle(0, "carbon emission");

        art.removeUserLike(a);

        articleLikeSetCheck(art, new ArrayList<User>());
    }


    // Helper functions //
    private void articleLikeSetCheck(Article article, List<User> likers) {
        for (int i = 0; i < likers.size(); assertTrue(article.getLikes().contains(likers.get(i++))));
    }
}
