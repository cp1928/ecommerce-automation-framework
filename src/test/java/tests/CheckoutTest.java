package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.DriverFactory;
import utils.ScreenshotUtil;

public class CheckoutTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver(System.getProperty("browser"));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        cartPage.goToCart();
        cartPage.clickCheckout();
    }

    @Test
    public void testCompleteCheckout() {
        checkoutPage.enterShippingInfo("John", "Doe", "12345");
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test
    public void testMissingFirstName() {
        checkoutPage.enterShippingInfo("", "Doe", "12345");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test
    public void testMissingLastName() {
        checkoutPage.enterShippingInfo("John", "", "12345");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name is required"));
    }

    @Test
    public void testMissingZipCode() {
        checkoutPage.enterShippingInfo("John", "Doe", "");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"));
    }

    @Test
    public void testAllFieldsEmpty() {
        checkoutPage.enterShippingInfo("", "", "");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test
    public void testCancelButtonOnStepOne() {
        checkoutPage.clickCancelStepOne();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test
    public void testCancelButtonOnOverview() {
        checkoutPage.enterShippingInfo("John", "Doe", "12345");
        checkoutPage.clickCancelOverview();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testOrderTotalIsCalculated() {
        checkoutPage.enterShippingInfo("John", "Doe", "12345");
        String totalText = checkoutPage.getTotalPriceText();
        Assert.assertTrue(totalText.contains("Total"));
    }

    @Test
    public void testItemQuantityShownCorrectly() {
        checkoutPage.enterShippingInfo("John", "Doe", "12345");
        Assert.assertEquals(checkoutPage.getItemQuantity(), "1");
    }

    @Test
    public void testBackHomeButtonWorksAfterOrder() {
        checkoutPage.enterShippingInfo("John", "Doe", "12345");
        checkoutPage.clickFinish();
        checkoutPage.getConfirmationMessage();
        checkoutPage.clickBackHome();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testCheckoutPageTitle() {
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtil.takeScreenshot(driver, result.getName());
            System.out.println("Screenshot saved at: " + path);
        }
        driver.quit();
    }
}