package lib.ui;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
        TITLE,
        SECOND_TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        LIST_FOLDER_BY_NAME_TPL,
        OPTIONS_ADD_TO_MY_LIST_AFTER_AUTH,
        FIRST_BOLD_ARTICLE_WORD;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getListFolderXpathByName(String name_of_folder)
    {
        return LIST_FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    /*TEMPLATES METHODS*/

    public WebElement waitForTitleElement()
    {
       return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public WebElement waitForTitleElementForSecondArticle()
    {
        return this.waitForElementPresent(SECOND_TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public WebElement waitForFirstBoldArticleWord()
    {
        return this.waitForElementPresent(FIRST_BOLD_ARTICLE_WORD, "Cannot find first bold article word", 15);
    }
    public String getArticleFirstBoldArticleWord() {
        WebElement bold_word_element = waitForFirstBoldArticleWord();
        if (Platform.getInstance().isMW()) {
            return bold_word_element.getText();
        } else {
            System.out.println("Method waitForFirstBoldArticleWord does nothing for platform " + Platform.getInstance().getPlatformVar());
        return null;
    }}

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of article", 40);
    } else if (Platform.getInstance().isIOS()) { this.swipeUpTillElementAppear(FOOTER_ELEMENT,
            "Cannot find the end of article",
                40); }
        else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,
                    "Cannot find the end of article",
            40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot Find More Options",
                10);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot fing 'Got it' tip overlay",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input for a reading list",
                5);


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                (name_of_folder),
                "Cannot put text into articles folder title",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find OK",
                5);
    }

    public void closeArticle()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS())) {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, Cannot find X",
                5);
    } else {
            System.out.println(" Method closArticle does nothing for " + Platform.getInstance().getPlatformVar());
        }
    }

    public void assertArticleTitlePresents ()

    {
        this.assertElementPresent(TITLE, "We didnt find any item");
    }

    public void addArticleToExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot Find More Options", 10);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST, "Cannot find option to add article to reading list", 5);

        String lists_folder_xpath = getListFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                lists_folder_xpath,
                "Cannot find existing article folder",
                5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove added article", 2);
        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_AFTER_AUTH, "Cannot find button to add an atricle to saved articles after removing it from this list", 5);
        OPTIONS_ADD_TO_MY_LIST = OPTIONS_ADD_TO_MY_LIST_AFTER_AUTH;
        }
    }

    public void addArticlesToMySaved()
    {
        String remember_string = OPTIONS_ADD_TO_MY_LIST;
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST, "Cannot find option to add article to my reading list", 5);
    OPTIONS_ADD_TO_MY_LIST = remember_string;
    }
    public void addArticlesToMySavedAfterAuth()
    {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }

        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_AFTER_AUTH, "Cannot find option to add article to my reading list", 5);
        } else {
            System.out.println("Method addArticlesToMySavedAfterAuth does nothing for Platform " +Platform.getInstance().getPlatformVar());
        }


    }
}
