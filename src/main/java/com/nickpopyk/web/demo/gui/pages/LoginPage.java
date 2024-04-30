package com.nickpopyk.web.demo.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

import static com.nickpopyk.web.demo.utils.IConstants.FIVE_SEC_TIMEOUT;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class, 'normal-text')]/h3")
    private ExtendedWebElement loginResultTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageURL("/login.php3");
    }

    public boolean isLoginResultTitlePresent(){
        return loginResultTitle.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public String getLoginResultTitleText(){
        return loginResultTitle.getText();
    }
}
