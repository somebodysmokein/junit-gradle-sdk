package com.browserstack.examples.tests.login;

import com.browserstack.examples.helpers.BaseTest;
import com.browserstack.examples.utils.UserCredentialUtil;
import com.browserstack.examples.helpers.CommonSteps;
import com.browserstack.examples.helpers.ElementLocatorUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.browserstack.examples.helpers.Constants.AllureTags.FEATURE_LOGIN;
import static com.browserstack.examples.helpers.Constants.AllureTags.STORY_ACCOUNT_LOCK;
import static com.browserstack.examples.helpers.Constants.ElementLocators.*;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.*;

@Feature(FEATURE_LOGIN)
@Story(STORY_ACCOUNT_LOCK)
public class AccountLockTest extends BaseTest {

    private static final String ACCOUNT_LOCKED_ERROR_MESSAGE = "Your account has been locked.";
    private static final String LOCKED_ACCOUNT_USER_NAME = "locked_user";

    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void lockMessageTest() {
        CommonSteps.navigateToHome();
        signIntoLockedAccount();
        checkAPIErrorDisplayed();
    }

    @Step("Attempting to sign in with locked_user credentials")
    private void signIntoLockedAccount() {
        String password = UserCredentialUtil.getPassword(LOCKED_ACCOUNT_USER_NAME);
        attemptSignIn(LOCKED_ACCOUNT_USER_NAME, password);
        ElementLocatorUtil.waitUntilElementAppears(By.className(API_ERROR_CLASS), API_ERROR_NOT_LOADED_ON_TIME);
    }

    @Step("Attempting to sign in with username {1} and password {2}")
    private void attemptSignIn(String userName, String password) {
        WebElement signInButton = webDriver.findElement(By.id(SIGN_IN_BUTTON_ID));
        signInButton.click();
        ElementLocatorUtil.waitUntilElementAppears(By.id(USER_INPUT_ID), SIGNIN_PAGE_NOT_LOADED_ON_TIME);
        WebElement userElement = webDriver.findElement(By.id(USER_INPUT_ID));
        userElement.sendKeys(userName);
        userElement.sendKeys(Keys.ENTER);
        WebElement passwordElement = webDriver.findElement(By.id(PASSWORD_INPUT_ID));
        passwordElement.sendKeys(password);
        passwordElement.sendKeys(Keys.ENTER);
        WebElement logInButton = webDriver.findElement(By.id(LOGIN_BUTTON_ID));
        logInButton.click();
    }

    @Step("Checking if the API error is displayed")
    private void checkAPIErrorDisplayed() {
        WebElement apiError = webDriver.findElement(By.className(API_ERROR_CLASS));
        Assertions.assertEquals(apiError.getText(), ACCOUNT_LOCKED_ERROR_MESSAGE, LOCKED_ACCOUNT);
    }

}