package com.nickpopyk.web.demo.gui.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

import static com.nickpopyk.web.demo.utils.IConstants.THREE_SEC_TIMEOUT;

public class ResultsPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='st-text']/p[contains(text(), 'Your search returned')]")
    private ExtendedWebElement resultText;

    @FindBy(xpath = "//div[@class='st-text']/p/a[contains(text(), 'click here')]")
    private ExtendedWebElement clickHereButton;

    @FindBy(xpath = "//div[@id='review-body']/div/ul/li/a/strong/span")
    private List<ExtendedWebElement> phonesTitles;

    @FindBy(xpath = "//div[@class='st-text']/p[@class='note']")
    private ExtendedWebElement noteText;

    public ResultsPage(WebDriver driver) {
        super(driver);
        setPageURL("/results.php3");
    }

    public boolean isResultTextPresent(){
        return resultText.isElementPresent(THREE_SEC_TIMEOUT);
    }

    public boolean isClickHereButtonPresent(){
        return clickHereButton.isElementPresent(THREE_SEC_TIMEOUT);
    }

    public boolean isNoteTextPresent(){
        return noteText.isElementPresent(THREE_SEC_TIMEOUT);
    }

    public String getResultText(){
        return resultText.getText();
    }

    public List<String> getPhonesTitles(){
        return phonesTitles.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
    }

    public int getResultsNumber(){
        String text = resultText.getText();
        Pattern pattern = Pattern.compile("Your search returned (.*?) results");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;
    }

    public String getNoteText(){
        return noteText.getText();
    }

    public PhoneFinderPage clickClickHereButton(){
        clickHereButton.click();
        return new PhoneFinderPage(driver);
    }
}
