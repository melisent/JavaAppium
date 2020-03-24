package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST = "css:#ca-watch[title='Watch'][role='button']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch[title='Unwatch'][role='button']";
        OPTIONS_ADD_TO_MY_LIST_AFTER_AUTH = "css:#ca-watch[title='Add this page to your watchlist']";
        FIRST_BOLD_ARTICLE_WORD = "css:#mf-section-0 b";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
