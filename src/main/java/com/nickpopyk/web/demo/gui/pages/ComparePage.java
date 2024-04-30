package com.nickpopyk.web.demo.gui.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class ComparePage extends AbstractPage {

    @FindBy(xpath = "//span[@data-spec='modelname']")
    private List<ExtendedWebElement> phonesToCompare;

    @FindBy(xpath = "//div[contains(@class, 'compare-candidate')]/div[contains(@class, 'candidate-search')]/form/input[contains(@name, 'sSearch')]")
    private List<ExtendedWebElement> candidateSearchInputs;

    @FindBy(xpath = "//div[contains(@class, 'compare-candidate')]/div[contains(@class, 'candidate-search')]/form/input[@type='submit']")
    private List<ExtendedWebElement> candidateSearchSubmitButtons;

    @FindBy(xpath = "//th")
    private List<ExtendedWebElement> headerCells;

    public ComparePage(WebDriver driver) {
        super(driver);
        setPageURL("/compare.php3");
    }

    public List<String> getPhonesToCompare(){
        return phonesToCompare.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
    }

    public List<String> getHeaderCells(){
        return headerCells.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
    }

    public void searchCandidate(int number, String toSearch){
        candidateSearchInputs.get(number).type(toSearch);
        candidateSearchSubmitButtons.get(number).click();
    }

    public int getColumnsOfRow(String rowName){
        ExtendedWebElement headerCell = headerCells.stream().filter(element -> element.getText().toLowerCase().contains(rowName.toLowerCase())).findAny().orElse(null);
        return headerCell.getElement().findElements(By.xpath("//th[text()='Network']/following-sibling::*")).size() + 1;
    }
}
