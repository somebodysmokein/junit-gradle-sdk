package com.browserstack.examples.pages;

import com.browserstack.examples.utils.LoggedOutNavBarComponent;
import com.browserstack.examples.utils.NavBarComponent;
import org.openqa.selenium.WebDriver;

public abstract class NavigablePage {

    private NavBarComponent navBarComponent;

    public NavigablePage(WebDriver webDriver) {
        this.navBarComponent = new LoggedOutNavBarComponent(webDriver);
    }

    public NavigablePage(NavBarComponent navBarComponent) {
        this.navBarComponent = navBarComponent;
    }

    public NavBarComponent getNavBarComponent() {
        return navBarComponent;
    }

    public void setNavBarComponent(NavBarComponent navBarComponent) {
        this.navBarComponent = navBarComponent;
    }
}