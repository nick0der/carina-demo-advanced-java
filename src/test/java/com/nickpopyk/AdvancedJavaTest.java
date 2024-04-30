package com.nickpopyk;

import org.testng.annotations.Test;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class AdvancedJavaTest implements IAbstractTest {

    @Test(description = "Verify opinions on phone page")
    @MethodOwner(owner = "nick0der")
    public void testVerifyOpinionsOnPhonePage() {

    }
}
