package com.nopcommerce.testsuite;

import com.nopcommerce.customlisteners.CustomListeners;
import com.nopcommerce.pages.*;
import com.nopcommerce.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(CustomListeners.class)
public class ElectronicsMenuTest extends BaseTest {

    HomePage homePage;
    ComputerPage computerPage;
    DesktopPage desktopPage;
    AddToCartPage addToCartPage;
    ShoppingCartPage shoppingCartPage;
    CheckOutPage checkOutPage;
    GuestCheckOutPage guestCheckOutPage;
    CellPhonesPage cellPhonesPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopPage = new DesktopPage();
        addToCartPage = new AddToCartPage();
        shoppingCartPage = new ShoppingCartPage();
        checkOutPage = new CheckOutPage();
        guestCheckOutPage = new GuestCheckOutPage();
        cellPhonesPage = new CellPhonesPage();
    }


    @Test(groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        // Mouse Hover on “Electronics” Tab
        homePage.mouseHoverToElectronic();
        // Mouse Hover on “Cell phones” and click
        Thread.sleep(1000);
        homePage.mouseHoverToElectronicAndClickOnCellPhone();
        // Verify the text “Cell phones”
        Assert.assertEquals(cellPhonesPage.verifyTextCellPhone(), "Cell phones");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        // Mouse Hover on “Electronics” Tab
        homePage.mouseHoverToElectronic();
        //Mouse Hover on “Cell phones” and click
        homePage.mouseHoverToElectronicAndClickOnCellPhone();
        //Verify the text “Cell phones”
        Assert.assertEquals(cellPhonesPage.verifyTextCellPhone(), "Cell phones");
        // Click on List View Tab
        cellPhonesPage.clickOnListView();
        // Click on product name “Nokia Lumia 1020” link
        Thread.sleep(2000);
        // waitUntilVisibilityOfElementLocated(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"),5);
        cellPhonesPage.clickOnNokiaLumia1020();
        //Verify the text “Nokia Lumia 1020”
        Assert.assertEquals(cellPhonesPage.verifyTextNokiaLumia1020(), "Nokia Lumia 1020");
        //Verify the price “$349.00”
        Assert.assertEquals(cellPhonesPage.verifyPrice(), "$349.00");
        // Change quantity to 2
        cellPhonesPage.changeThatQty();
        // Click on “ADD TO CART” tab
        cellPhonesPage.clickOnAddToCart();
        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        Assert.assertEquals(cellPhonesPage.verifyAddToCartSuccessfully(), "The product has been added to your shopping cart");
        //After that close the bar clicking on the cross button.
        cellPhonesPage.clickOnCrossButton();
        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        cellPhonesPage.mouseHoverToShoppingCart();
        // Verify the message "Shopping cart"
        shoppingCartPage.verifyShoppingCartText();
        // Verify the quantity is 2
        Assert.assertEquals(shoppingCartPage.verifyQty(), "2");
        // Verify the Total $698.00
        Assert.assertEquals(shoppingCartPage.verifyPriceText(), "$698.00");
    }
}
