package com.nickpopyk.web.demo.gui.components;

import com.nickpopyk.web.demo.gui.pages.LoginPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static com.nickpopyk.web.demo.utils.IConstants.*;

public class LoginPopUp extends AbstractUIObject {

    @FindBy(xpath = "//form[@action='login.php3']/p")
    private ExtendedWebElement loginTitle;

    @FindBy(xpath = "//form[@action='login.php3']/input[@type='email']")
    private ExtendedWebElement emailTextField;

    @FindBy(xpath = "//form[@action='login.php3']/input[@type='password']")
    private ExtendedWebElement passwordTextField;

    @FindBy(xpath = "//form[@action='login.php3']/input[@type='submit']")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = "//a[@href='forgot.php3']")
    private ExtendedWebElement forgotPasswordLink;

    public LoginPopUp(WebDriver driver) {
        super(driver);
    }

    public LoginPopUp(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isLoginTitlePresent(){
        return loginTitle.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isEmailTextFieldPresent(){
        return emailTextField.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isPasswordTestFieldPresent(){
        return passwordTextField.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isSubmitButtonPresent(){
        return submitButton.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isForgotPasswordLinkPresent(){
        return forgotPasswordLink.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public LoginPopUp typeEmail(String email){
        emailTextField.type(email);
        return this;
    }

    public LoginPopUp typePassword(String password){
        passwordTextField.type(password);
        return this;
    }

    public LoginPage clickSubmitButton(){
        submitButton.click();
        return new LoginPage(driver);
    }
}