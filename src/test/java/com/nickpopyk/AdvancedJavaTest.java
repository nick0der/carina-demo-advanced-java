package com.nickpopyk;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nickpopyk.web.demo.gui.pages.HomePage;
import com.nickpopyk.web.demo.gui.pages.PhonePage;
import com.nickpopyk.web.demo.gui.pages.PhonesPage;
import com.nickpopyk.web.demo.gui.pages.ReviewsPage;
import com.nickpopyk.web.demo.utils.SortReviewsBy;
import com.nickpopyk.web.demo.connectionpool.Connection;
import com.nickpopyk.web.demo.connectionpool.ConnectionPool;
import com.nickpopyk.web.demo.connectionpool.MyThread;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

import static com.nickpopyk.web.demo.connectionpool.ConnectionProcessor.processConnection;

public class AdvancedJavaTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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

    @Test(description = "Test connection")
    @MethodOwner(owner = "nick0der")
    public void testConnection() {
        Queue<Connection> pool = ConnectionPool.getInstance(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(7);
        for (int i = 0; i < 7; i++) {
            threadPool.submit(new MyThread(pool));
        }
        threadPool.shutdown();
        // Wait for the thread pool to terminate
        try {
            boolean isTerminated = threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            LOGGER.info("Thread pool is terminated: {}", isTerminated);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Running using CompletableFuture:\n");

        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            tasks.add(CompletableFuture.runAsync(() -> processConnection(pool, new Random().nextInt(4001) + 1000)));
        }
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
        allTasks.join();
    }
}
