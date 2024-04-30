package com.nickpopyk.web.demo.gui.pages;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nickpopyk.web.demo.utils.SortReviewsBy;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

import static com.nickpopyk.web.demo.utils.IConstants.THREE_SEC_TIMEOUT;

public class ReviewsPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "(//select[@name='nSortNew'])[1]")
    private ExtendedWebElement sortBySelect;

    @FindBy(xpath = "//ul[contains(@class, 'votes')]")
    private List<ExtendedWebElement> votesList;

    @FindBy(xpath = "//li[contains(@class, 'uvote')]")
    private List<ExtendedWebElement> reactionButtons;

    @FindBy(xpath = "//span[contains(@class, 'upvote')]")
    private ExtendedWebElement upvoteButton;

    @FindBy(xpath = "//li[contains(@class, 'upost')]/time")
    private List<ExtendedWebElement> datesList;

    public ReviewsPage(WebDriver driver, String url) {
        super(driver);
        setPageAbsoluteURL(url);
    }

    public void setSortBySelect(SortReviewsBy by){
        sortBySelect.select(by.getValue());
    }

    public void voteComment(int index, boolean upvote){
        if (reactionButtons.get(index).isElementNotPresent(THREE_SEC_TIMEOUT)) {
            Assert.fail("Reaction buttons are not present. Maybe you are not logged in or index is out of range.");
        }
        reactionButtons.get(index).click();
        if (upvote){
            if (upvoteButton.getText().toLowerCase().contains("downvote")){
                LOGGER.info("Upvote is already present");
            } else {
                upvoteButton.click();
            }
        } else {
            if (upvoteButton.getText().toLowerCase().contains("upvote")){
                LOGGER.info("Comment is already not upvote");
            } else {
                upvoteButton.click();
            }
        }
    }

    public int getCommentRating(int index){
        if (votesList.get(index).getElement().findElements(By.xpath(".//*")).size() == 0){
            return 0;
        } else if (votesList.get(index).getElement().findElements(By.xpath("./li/span")).size() != 0) {
            return Integer.parseInt(votesList.get(index).getElement().findElement(By.xpath("./li/span")).getText());
        } else return 1;
    }
}
