package com.nickpopyk.web.demo.gui.pages;

import com.nickpopyk.web.demo.utils.Dropdowns;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

import static com.nickpopyk.web.demo.utils.IConstants.THREE_SEC_TIMEOUT;

public class PhoneFinderPage extends AbstractPage {

    @FindBy(xpath = "//button//*[contains (text(), '%s' )]/parent::button")
    private ExtendedWebElement dropdownButtonByText;

    @FindBy(xpath = "//button//*[contains (text(), '%s' )]/parent::button/following-sibling::div/ul/li/a/span[text()='%s']")
    private ExtendedWebElement dropdownOptionByText;

    @FindBy(xpath = "//span[@class='pf-border']")
    private ExtendedWebElement showButton;

    public PhoneFinderPage(WebDriver driver) {
        super(driver);
        setPageURL("/search.php3");
    }

    public boolean isShowButtonPresent(){
        return showButton.isElementPresent(THREE_SEC_TIMEOUT);
    }

    public boolean doesShowButtonHaveResultsText(){
        return showButton.getText().matches("[0-9]+ results");
    }

    public int getResultsNumber(){
        return Integer.parseInt(showButton.getText().replaceAll("[^0-9]", ""));
    }

    public void chooseDropdownOption(Dropdowns text, String option){
        dropdownButtonByText.format(text.getValue()).click();
        dropdownOptionByText.format(text.getValue(), option).click();
    }

    public ResultsPage clickShowButton(){
        showButton.click();
        return new ResultsPage(driver);
    }
}
