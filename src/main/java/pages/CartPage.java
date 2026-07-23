package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;

    By cartIcon = By.className("shopping_cart_link");
    By cartBadge = By.className("shopping_cart_badge");
    By cartItems = By.className("cart_item");
    By cartItemNames = By.className("inventory_item_name");
    By cartItemPrices = By.className("inventory_item_price");
    By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    By continueShoppingButton = By.id("continue-shopping");
    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }

    public boolean isCartBadgeDisplayed() {
        return driver.findElements(cartBadge).size() > 0;
    }

    public int getNumberOfItemsInCart() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public void removeBackpackFromCart() {
        driver.findElement(removeBackpackButton).click();
    }

    public String getFirstCartItemName() {
        return driver.findElements(cartItemNames).get(0).getText();
    }

    public String getFirstCartItemPrice() {
        return driver.findElements(cartItemPrices).get(0).getText();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}