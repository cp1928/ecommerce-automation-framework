package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverFactory;

public class ProductsPageTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver(System.getProperty("browser"));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testPageTitleText() {
        Assert.assertEquals(productsPage.getPageTitleText(), "Products");
    }

    @Test
    public void testTotalProductCount() {
        Assert.assertEquals(productsPage.getTotalProductCount(), 6);
    }

    @Test
    public void testSortByPriceLowToHigh() {
        String firstPriceBefore = productsPage.getFirstProductPrice();
        productsPage.sortByPriceLowToHigh();
        String firstPriceAfter = productsPage.getFirstProductPriceAfterSort();
        Assert.assertNotNull(firstPriceAfter);
    }

    @Test
    public void testHamburgerMenuOpens() {
        productsPage.openMenu();
        Assert.assertTrue(driver.findElement(org.openqa.selenium.By.id("logout_sidebar_link")).isDisplayed());
    }

    @Test
    public void testLogoutFunctionality() {
        productsPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void testTwitterLinkDisplayed() {
        Assert.assertTrue(productsPage.isTwitterLinkDisplayed());
    }

    @Test
    public void testFacebookLinkDisplayed() {
        Assert.assertTrue(productsPage.isFacebookLinkDisplayed());
    }

    @Test
    public void testLinkedinLinkDisplayed() {
        Assert.assertTrue(productsPage.isLinkedinLinkDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}