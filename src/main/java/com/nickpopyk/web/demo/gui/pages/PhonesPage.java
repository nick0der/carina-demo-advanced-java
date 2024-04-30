package com.nickpopyk.web.demo.gui.pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

import java.util.List;

public class PhonesPage extends AbstractPage {

    @FindBy(xpath = "//ul[@class='article-info-meta']/li[contains(@class, 'popularity')]/a")
    private ExtendedWebElement popularityButton;

    @FindBy(xpath = "//div[contains(@class, 'makers')]/ul/li")
    private List<ExtendedWebElement> phones;

    @FindBy(xpath = "//ul[@class='article-info-meta']/li[contains(@class, 'compare')]")
    private ExtendedWebElement compareButton;

    public PhonesPage(WebDriver driver, String url) {
        super(driver);
        setPageAbsoluteURL(url);
    }

    public void selectPopularityTab(){
        popularityButton.click();
    }

    public PhonePage openPhone(int index){
        String url = phones.get(index).getElement().findElement(By.xpath("./a")).getAttribute("href");
        phones.get(index).click();
        return new PhonePage(driver, url);
    }

    public void selectPhone(String title){
        Actions action = new Actions(driver);
        ExtendedWebElement phone = phones.stream().filter(element -> element.getText().equalsIgnoreCase(title)).findAny().orElse(null);
        Assert.notNull(phone, "Cannot find phone with title '" + title +"'");
        action.moveToElement(phone.getElement()).moveByOffset(phone.getSize().getWidth() / 3, phone.getSize().getHeight() / 3).click().build().perform();
    }

    public String getPhoneTitle(int index){
        return phones.get(index).getText();
    }

    public ComparePage clickCompareButton(){
        int checkedPhones = getNumberOfSelectedPhones();
        compareButton.click();
        return checkedPhones == 0 ? null : new ComparePage(driver);
    }

    public boolean isSelected(String title){
        ExtendedWebElement phone = phones.stream().filter(element -> element.getText().equalsIgnoreCase(title)).findAny().orElse(null);
        Assert.notNull(phone, "Cannot find phone with title '" + title +"'");
        return phone.getAttribute("class").toLowerCase().contains("checked");
    }

    public int getNumberOfSelectedPhones(){
        return driver.findElements(By.xpath("//li[contains(@class, 'checked')]")).size();
    }

    public int getCompareButtonNumber(){
        String result = compareButton.getText().replaceAll("\\D+", "");
        return result.isEmpty() ? 0 : Integer.parseInt(result);
    }
}
