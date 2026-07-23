package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import utils.DriverFactory;

public class CartTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver(System.getProperty("browser"));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testAddItemToCart() {
        productsPage.addBackpackToCart();
        Assert.assertEquals(cartPage.getCartBadgeCount(), "1");
    }

    @Test
    public void testItemAppearsInCartPage() {
        productsPage.addBackpackToCart();
        cartPage.goToCart();
        Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 1);
    }

    @Test
    public void testAddMultipleItemsToCart() {
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        productsPage.addBoltShirtToCart();
        Assert.assertEquals(cartPage.getCartBadgeCount(), "3");
    }

    @Test
    public void testRemoveItemFromCart() {
        productsPage.addBackpackToCart();
        Assert.assertEquals(cartPage.getCartBadgeCount(), "1");
        cartPage.removeBackpackFromCart();
        Assert.assertFalse(cartPage.isCartBadgeDisplayed());
    }

    @Test
    public void testCartBadgeNotDisplayedWhenEmpty() {
        Assert.assertFalse(cartPage.isCartBadgeDisplayed());
    }

    @Test
    public void testCartBadgeCountIncreasesCorrectly() {
        productsPage.addBackpackToCart();
        Assert.assertEquals(cartPage.getCartBadgeCount(), "1");
        productsPage.addBikeLightToCart();
        Assert.assertEquals(cartPage.getCartBadgeCount(), "2");
    }

    @Test
    public void testContinueShoppingButtonWorks() {
        productsPage.addBackpackToCart();
        cartPage.goToCart();
        cartPage.clickContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testItemNameMatchesBetweenProductAndCart() {
        String productName = productsPage.getFirstProductName();
        productsPage.addBackpackToCart();
        cartPage.goToCart();
        Assert.assertEquals(cartPage.getFirstCartItemName(), productName);
    }

    @Test
    public void testItemPriceMatchesBetweenProductAndCart() {
        String productPrice = productsPage.getFirstProductPrice();
        productsPage.addBackpackToCart();
        cartPage.goToCart();
        Assert.assertEquals(cartPage.getFirstCartItemPrice(), productPrice);
    }

    @Test
    public void testAddAllProductsToCart() {
        productsPage.addAllProductsToCart();
        Assert.assertEquals(cartPage.getCartBadgeCount(), "6");
    }

    @Test
    public void testCartPersistsAfterRefresh() {
        productsPage.addBackpackToCart();
        driver.navigate().refresh();
        Assert.assertEquals(cartPage.getCartBadgeCount(), "1");
    }

    @Test
    public void testTotalProductCountOnPage() {
        Assert.assertEquals(productsPage.getTotalProductCount(), 6);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}