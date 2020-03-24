package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
        MY_LISTS_LINK,
        OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }


     public void clickMyLists()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to my lists",
                    10
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    10
            );
        }
    }

    public void openNavigation(){
        if (Platform.getInstance().isMW()) {
        this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot open navigation", 5);
    } else {
            System.out.println("Method openNavigation() does nothing fot platform" + Platform.getInstance().getPlatformVar());
        }
        }
    }

