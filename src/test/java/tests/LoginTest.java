package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver(System.getProperty("browser"));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testInvalidPassword() {
        loginPage.login("standard_user", "wrong_password");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @Test
    public void testInvalidUsername() {
        loginPage.login("wrong_user", "secret_sauce");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @Test
    public void testEmptyUsername() {
        loginPage.login("", "secret_sauce");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username is required"));
    }

    @Test
    public void testEmptyPassword() {
        loginPage.login("standard_user", "");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Password is required"));
    }

    @Test
    public void testBothFieldsEmpty() {
        loginPage.login("", "");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username is required"));
    }

    @Test
    public void testLockedOutUser() {
        loginPage.login("locked_out_user", "secret_sauce");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Sorry, this user has been locked out"));
    }

    @Test
    public void testLoginPageTitle() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Swag Labs");
    }

    @Test
    public void testLoginButtonIsDisplayed() {
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}