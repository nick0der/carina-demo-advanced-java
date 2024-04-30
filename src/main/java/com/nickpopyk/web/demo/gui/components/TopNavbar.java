package com.nickpopyk.web.demo.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

import static com.nickpopyk.web.demo.utils.IConstants.*;

public class TopNavbar extends AbstractUIObject {

    @FindBy(xpath = "//div/a[@class='login-icon']")
    private ExtendedWebElement logInButton;

    @FindBy(xpath = "//a[@href='account.php3']")
    private ExtendedWebElement accountButton;

    @FindBy(xpath = "//span[@id='login-popup2']")
    private LoginPopUp loginPopUp;

    public TopNavbar(WebDriver driver) {
        super(driver);
    }

    public TopNavbar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public LoginPopUp getLoginPopUp(){
        return loginPopUp;
    }

    public boolean isLoginButtonPresent(){
        return logInButton.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isAccountButtonPresent(){
        return accountButton.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isLoginPopUpPresent(){
        return loginPopUp.isUIObjectPresent(FIVE_SEC_TIMEOUT);
    }

    public LoginPopUp openLoginPopUp(){
        logInButton.click();
        return loginPopUp;
    }
}
