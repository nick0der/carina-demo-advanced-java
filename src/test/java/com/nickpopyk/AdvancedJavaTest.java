package com.nickpopyk;

import java.util.*;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nickpopyk.web.demo.gui.pages.HomePage;
import com.nickpopyk.web.demo.gui.pages.PhonePage;
import com.nickpopyk.web.demo.gui.pages.PhonesPage;
import com.nickpopyk.web.demo.gui.pages.ReviewsPage;
import com.nickpopyk.web.demo.utils.SortReviewsBy;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class AdvancedJavaTest implements IAbstractTest {

    class ReverseComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T obj1, T obj2) {
            return obj2.compareTo(obj1); // Reverses the comparison result
        }
    }

    @Test(description = "Verify opinions on phone page")
    @MethodOwner(owner = "nick0der")
    public void testVerifyOpinionsOnPhonePage() {
        String brand = "Apple";
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        // Open reviews and verify sorting by 'Best rating'
        PhonesPage phonesPage = homePage.getBrandsComponent().openBrandLink(brand);
        phonesPage.selectPopularityTab();
        PhonePage phonePage = phonesPage.openPhone(0);
        ReviewsPage reviewsPage = phonePage.openOpinionsTab();
        Assert.assertTrue(reviewsPage.isPageOpened(), "Reviews page is not opened");
        reviewsPage.setSortBySelect(SortReviewsBy.RATING);
        List<Integer> sortedVotes = reviewsPage.getVotesList().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assert.assertEquals(reviewsPage.getVotesList(), sortedVotes, "Reviews are not sorted correctly by rating");

        // Check sorting by 'Newest first'
        reviewsPage.setSortBySelect(SortReviewsBy.NEWEST);
        List<Date> reviewDates = reviewsPage.getDatesList();
        List<Date> sortedDates = reviewDates.stream().sorted(new ReverseComparator<Date>()).collect(Collectors.toList());
        for (int i = 0; i < reviewDates.size(); i++) {
            Assert.assertEquals(reviewDates.get(i).toString(), sortedDates.get(i).toString(), "Reviews are not sorted correctly by date");
        }
    }
}
