package com.nickpopyk.web.demo.gui.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

import static com.nickpopyk.web.demo.utils.IConstants.FIVE_SEC_TIMEOUT;

public class NewsPage extends AbstractPage {

    @FindBy(xpath = "//input[@class='searchFor']")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = "//input[@class='submit button button-small']")
    private ExtendedWebElement searchButton;

    @FindBy(xpath = "//h1[@class='article-info-name']")
    private ExtendedWebElement articleInfoName;

    @FindBy(xpath = "//div[@class='floating-title']/div/a/h3")
    private List<ExtendedWebElement> articleTitles;

    public NewsPage(WebDriver driver) {
        super(driver);
        setPageURL("/news.php3");
    }

    public boolean isSearchInputPresent(){
        return searchInput.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isSearchButtonPresent(){
        return searchButton.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public boolean isArticleInfoNamePresent(){
        return articleInfoName.isElementPresent(FIVE_SEC_TIMEOUT);
    }

    public void typeSearchText(String text){
        searchInput.type(text);
    }

    public void clickSearchButton(){
        searchButton.click();
    }

    public String getArticleInfoName(){
        return articleInfoName.getText();
    }

    public List<String> getArticleTitles(){
        return articleTitles.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
    }
}
