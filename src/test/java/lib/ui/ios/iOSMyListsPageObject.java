package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {
    static
    {
    ARTICLE_BY_TITLE_TPL = "xpath://XCUIElement[contains(@name='{TITLE}')]";
    SAVED_ARTICLES_ELEMENT = "xpath://XCUIElementTypeCell";
    SEARCH_ELEMENT_AT_MY_LISTS = "xpath://XCUIElementTypeSearchField[@name='Search']";


    }

    public iOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
