package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON= "xpath://body/div[4]/div[2]/a[text()='Log in']",
    LOGIN_INPUT="css:#wpName1",
    PASSWORD_INPUT="css:#wpPassword1",
    SUBMIT_BUTTON="css:#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
       //this.waitForElementPresent(LOGIN_BUTTON, "Cannot find login button", 5);
     // this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and send keys input login",5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and send keys input password", 5);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit button", 5);
    }

    }

