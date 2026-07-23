package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By zipCodeField = By.id("postal-code");
    By continueButton = By.id("continue");
    By cancelButtonStepOne = By.id("cancel");
    By errorMessage = By.cssSelector("[data-test='error']");

    By finishButton = By.id("finish");
    By cancelButtonOverview = By.id("cancel");
    By confirmationMessage = By.className("complete-header");
    By backHomeButton = By.id("back-to-products");
    By totalPriceLabel = By.className("summary_total_label");
    By itemQuantity = By.className("cart_quantity");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterShippingInfo(String firstName, String lastName, String zip) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipCodeField).sendKeys(zip);
        driver.findElement(continueButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void clickCancelStepOne() {
        driver.findElement(cancelButtonStepOne).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public void clickCancelOverview() {
        driver.findElement(cancelButtonOverview).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).getText();
    }

    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }

    public String getTotalPriceText() {
        return driver.findElement(totalPriceLabel).getText();
    }

    public String getItemQuantity() {
        return driver.findElement(itemQuantity).getText();
    }
}