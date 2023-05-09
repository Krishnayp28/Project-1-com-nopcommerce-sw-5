package com.nopcommerce.testsuite;

import com.nopcommerce.customlisteners.CustomListeners;
import com.nopcommerce.pages.*;
import com.nopcommerce.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;


@Listeners(CustomListeners.class)
public class ComputerMenuTest extends BaseTest {


    HomePage homePage;
    ComputerPage computerPage;
    DesktopPage desktopPage;
    AddToCartPage addToCartPage;
    ShoppingCartPage shoppingCartPage;
    CheckOutPage checkOutPage;
    GuestCheckOutPage guestCheckOutPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopPage = new DesktopPage();
        addToCartPage = new AddToCartPage();
        shoppingCartPage = new ShoppingCartPage();
        checkOutPage = new CheckOutPage();
        guestCheckOutPage = new GuestCheckOutPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyProductArrangeInAlphabeticalOrder() {
        // Click on Computer Menu.
        homePage.clickOnComputerTab();
        // Click on Desktop
        computerPage.clickOnDesktop();
        ArrayList<String> originalProductList = desktopPage.defaultProductList();
        System.out.println(originalProductList);
        Collections.reverse(originalProductList);
        // Select Sort By position "Name: Z to A"
        System.out.println(originalProductList);
        desktopPage.sortByPositionByNameZtoA();
        ArrayList<String> afterSortByZtoAList = desktopPage.defaultProductList();
        System.out.println(originalProductList);
        // Verify the Product will arrange in Descending order.
        Assert.assertEquals(afterSortByZtoAList, afterSortByZtoAList);

    }

    @Test(groups = {"smoke", "regression"})
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //Click on Computer Menu.
        homePage.clickOnComputerTab();
        //Click on Desktop
        computerPage.clickOnDesktop();
        // Select Sort By position "Name: A to Z"
        desktopPage.sortByPositionByNameZtoA();
        //Click on "Add To Cart"
        Thread.sleep(1000);
        desktopPage.clickOnAddToCart();
        // Verify the Text "Build your own computer"
        Assert.assertEquals(addToCartPage.verifyBuildYourOwnComputerText(), "Build your own computer", "Not found");
        // Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        addToCartPage.selectProcessor();
        //Select "8GB [+$60.00]" using Select class.
        addToCartPage.selectRam();
        //Select HDD radio "400 GB [+$100.00]"
        addToCartPage.selectHDD();
        // Select OS radio "Vista Premium [+$60.00]"
        addToCartPage.selectOS();
        //Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        Thread.sleep(1000);
        addToCartPage.selectSoftware();
        //Verify the price "$1,475.00"
        Thread.sleep(2000);
        Assert.assertEquals(addToCartPage.verifyThePrice(), "$1,475.00", "Wrong Price");
        // Click on "ADD TO CARD" Button.
        addToCartPage.clickOnAddToCart();
        // Verify the Message "The product has been added to your shopping cart" on Top green Bar
        Assert.assertEquals(addToCartPage.verifyAddToCartSuccessfully(), "The product has been added to your shopping cart");
        //After that close the bar clicking on the cross button.
        addToCartPage.clickOnCrossButton();
        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        addToCartPage.mouceHoverToShopingCart();
        addToCartPage.clickOnGoToCart();
        // Verify the message "Shopping cart"
        Assert.assertEquals(shoppingCartPage.verifyShoppingCartText(), "Shopping cart");
        // Change the Qty to "2" and Click on "Update shopping cart"
        shoppingCartPage.changeTheQuantity();
        // Verify the Total"$2,950.00"
        Assert.assertEquals(shoppingCartPage.verifyTotalPrice(), "$2,950.00");
        // click on checkbox “I agree with the terms of service”
        shoppingCartPage.clickOnCheckBox();
        // Click on “CHECKOUT”
        shoppingCartPage.clickOnCheckOut();
        // Verify the Text “Welcome, Please Sign In!”
        Assert.assertEquals(checkOutPage.verifyWelcomeText(), "Welcome, Please Sign In!");
        //Click on “CHECKOUT AS GUEST” Tab
        checkOutPage.clickOnCheckOutAsGuest();
        // Fill the all mandatory field
        guestCheckOutPage.enterDetails();
        // Click on “CONTINUE”
        guestCheckOutPage.clickOnContinueButton();
        // Click on Radio Button “Next Day Air($0.00)”
        guestCheckOutPage.clickOnNextDayAir();
        //Click on “CONTINUE”
        guestCheckOutPage.clickOnContinue();
        //Select Radio Button “Credit Card”
        guestCheckOutPage.clickOnCreditCard();
        guestCheckOutPage.clickOnContinue1();
        //Select “Master card” From Select credit card dropdown
        guestCheckOutPage.selectMasterCard();
        // Fill all the details
        guestCheckOutPage.enterDetailsOfCard();
        //Click on “CONTINUE”
        guestCheckOutPage.clickOnContinueButton3();
        // Verify “Payment Method” is “Credit Card”
        Assert.assertEquals(guestCheckOutPage.verifyCreditCardPaymentMethod(), "Payment Method: Credit Card");
        // Verify “Shipping Method” is “Next Day Air”
        Assert.assertEquals(guestCheckOutPage.verifyShippingMethod(), "Shipping Method: Next Day Air");
        // Verify Total is “$2,950.00”
        Assert.assertEquals(guestCheckOutPage.verifyTotalPrice(), "$2,950.00");
        //Click on “CONFIRM”
        guestCheckOutPage.clickOnConfirm();
        // Verify the Text “Thank You”
        Assert.assertEquals(guestCheckOutPage.verifyThankYouMessage(), "Thank you");
        //Verify the message “Your order has been successfully processed!”
        Assert.assertEquals(guestCheckOutPage.verifyOrderPlacedSuccessfullyText(), "Your order has been successfully processed!");
        // Click on “CONTINUE”
        guestCheckOutPage.clickOnContinueButton4();
        //Verify the text “Welcome to our store”
        Assert.assertEquals(homePage.verifyWelcomeText(), "Welcome to our store");
    }
}
