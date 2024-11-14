package com.browserstack.examples.tests.offers;

import com.browserstack.examples.helpers.BaseTest;
import com.browserstack.examples.utils.UserCredentialUtil;
import com.browserstack.examples.helpers.ElementLocatorUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static com.browserstack.examples.helpers.CommonSteps.navigateToHome;
import static com.browserstack.examples.helpers.CommonSteps.signIn;
import static com.browserstack.examples.helpers.Constants.AllureTags.FEATURE_OFFERS;
import static com.browserstack.examples.helpers.Constants.AllureTags.STORY_REGIONAL_OFFERS;
import static com.browserstack.examples.helpers.Constants.ElementLocators.OFFERS_BUTTON_ID;
import static com.browserstack.examples.helpers.Constants.ElementLocators.OFFER_CARD_CLASS;
import static com.browserstack.examples.helpers.Constants.EndPoints.OFFERS;
import static com.browserstack.examples.helpers.Constants.EndPoints.SIGNED_IN;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.*;

@Feature(FEATURE_OFFERS)
@Story(STORY_REGIONAL_OFFERS)
public class RegionalOffersTest extends BaseTest {

    private static final String FAVOURITE_ACCOUNT_USER_NAME = "existing_orders_user";
    private static final String OFFER_MESSAGE = "We've promotional offers in your location.";
    private static final String LOCATION_SCRIPT_FORMAT = "navigator.geolocation.getCurrentPosition = function(success){\n" +
            "    var position = { \"coords\":{\"latitude\":\"%s\",\"longitude\":\"%s\"}};\n" +
            "    success(position);\n" +
            "}";
    private static final String OFFER_LATITUDE = "20";
    private static final String OFFER_LONGITUDE = "70";

    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void offersLoadedTest() {
        navigateToHome();
        signIntoFavouriteAccount();
        mockGPS();
        checkIfOfferMessageAvailable();
        checkIfOffersAreLoaded();
    }

    @Step("Signing in with fav_account credentials")
    private void signIntoFavouriteAccount() {
        String password = UserCredentialUtil.getPassword(FAVOURITE_ACCOUNT_USER_NAME);
        signIn(FAVOURITE_ACCOUNT_USER_NAME, password);
        ElementLocatorUtil.waitUntilURLContains(SIGNED_IN, SIGNIN_NOT_COMPLETED_ON_TIME);
    }

    @Step("Mocking the GPS location")
    private void mockGPS() {
        String locationScript = String.format(LOCATION_SCRIPT_FORMAT, OFFER_LATITUDE, OFFER_LONGITUDE);
        ((JavascriptExecutor) webDriver).executeScript(locationScript);
    }

    @Step("Checking if offers are available")
    private void checkIfOfferMessageAvailable() {
        webDriver.findElement(By.id(OFFERS_BUTTON_ID)).click();
        ElementLocatorUtil.waitUntilURLContains(OFFERS, OFFERS_PAGE_NOT_LOADED_ON_TIME);
        Assertions.assertTrue(webDriver.getPageSource().contains(OFFER_MESSAGE), OFFER_MESSAGES_NOT_FOUND);
    }

    @Step("Checking if offers are loaded")
    private void checkIfOffersAreLoaded() {
        ElementLocatorUtil.waitUntilElementAppears(By.className(OFFER_CARD_CLASS), NO_OFFERS_LOADED);
    }
}