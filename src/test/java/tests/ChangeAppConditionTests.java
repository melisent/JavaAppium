package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.time.Duration;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeOrientationOnSearchResults()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSub("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String titleBeforeRotation =  ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String titleAfterRotation =  ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after screen rotation",
                titleBeforeRotation,
                titleAfterRotation);

        this.rotateScreenPortrait();

        String titleAfterSecondRotation =  ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after screen rotation",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(Duration.ofMillis(200));
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
