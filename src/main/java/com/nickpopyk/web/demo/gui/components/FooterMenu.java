package com.nickpopyk.web.demo.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class FooterMenu extends AbstractUIObject {

    @FindBy(xpath = "//div[@class='footer-inner']//div[@id='footmenu']//a[@href='%s']")
    private ExtendedWebElement menuItemByLink;

    public FooterMenu(WebDriver driver) {
        super(driver);
    }

    public FooterMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}
