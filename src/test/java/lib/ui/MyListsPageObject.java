package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.rmi.Remote;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        REMOVED_FROM_SAVED_BUTTON,
        THE_ONE_SAVED_ARTICLE,
        SAVED_ARTICLES_ELEMENT,
        SEARCH_ELEMENT_AT_MY_LISTS;

    public MyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVED_FROM_SAVED_BUTTON.replace("{TITLE}",article_title);
    }
    /*TEMPLATES METHODS*/

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void waitFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(
                folder_name_xpath,
                "Cannot find article folder by name "+name_of_folder,
                5);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        if (Platform.getInstance().isAndroid()){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title "+article_title,
                15);
    } else return;

    }

    public void  swipeByArticleToDelete(String article_title)
    {

        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find Saved Article");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10);
        }

        if (Platform.getInstance().isIOS()) {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getFolderXpathByName(article_title);
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }


        this.waitForArticleToDisappearByTitle(article_title);

    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article with title "+article_title,
                15);
    }

    public void waitForArticleAndAClickByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot find saved article with title "+article_title,
                15);
    }

    public void openTheOneSavedArticle() {
        this.waitForElementAndClick(THE_ONE_SAVED_ARTICLE, "There are more than one saved article",5);
    }

    public void findSavedArticleByTitle(String article_title) {
        this.waitForElementAndClick(SEARCH_ELEMENT_AT_MY_LISTS, "Cannot find and click search element in My lists", 5);
        this.waitForElementAndSendKeys(
                SEARCH_ELEMENT_AT_MY_LISTS,
                article_title,
                "Cannot find and send keys to search element in My lists",
                5);

    }

    public int getAmountOfFoundSavedArticles()
    {
        this.waitForElementPresent(
               SAVED_ARTICLES_ELEMENT,
                "Cannot find anything by the request",
                20);

        return this.getAmountOfElements(SAVED_ARTICLES_ELEMENT);
    }

}
