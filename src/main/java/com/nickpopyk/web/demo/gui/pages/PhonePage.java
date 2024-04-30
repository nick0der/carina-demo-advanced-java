package com.nickpopyk.web.demo.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class PhonePage extends AbstractPage {

    @FindBy(xpath = "//h1[contains(@class, 'specs-phone-name-title')]")
    private ExtendedWebElement phoneTitle;

    @FindBy(xpath = "(//div[contains(@class, 'article-info-line')])[2]/ul/li/a/i[contains(@class, 'icon-comment-count')]/parent::a")
    private ExtendedWebElement opinionsButton;

    public PhonePage(WebDriver driver, String url) {
        super(driver);
        setPageAbsoluteURL(url);
    }

    public String getPhoneTitle(){
        return phoneTitle.getText();
    }

    public ReviewsPage openOpinionsTab(){
        String url = opinionsButton.getAttribute("href");
        opinionsButton.click();
        return new ReviewsPage(driver, url);
    }
}
