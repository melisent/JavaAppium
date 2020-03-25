package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {

        static {
            TITLE = "id:Java (programming language)";
            SECOND_TITLE = "";
            FOOTER_ELEMENT = "id:View article in browser";
            OPTIONS_ADD_TO_MY_LIST = "xpath://XCUIElementTypeButton[@name='Save for later']";
            CLOSE_ARTICLE_BUTTON = "id:Back";

        }

    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
