package com.nopcommerce.testsuite;

import com.nopcommerce.customlisteners.CustomListeners;
import com.nopcommerce.pages.ComputerPage;
import com.nopcommerce.pages.HomePage;
import com.nopcommerce.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(CustomListeners.class)
public class TopMenuTest extends BaseTest {

    HomePage homePage;
    ComputerPage computerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyPageNavigation() {
        homePage.selectMenu("Computers");
        String expectedMessage = "Computers";
        String actualMessage = computerPage.verifyComputerText();
        Assert.assertEquals(actualMessage, expectedMessage, "Computers page not display");
    }
}
