package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {
    WebDriver driver;

    By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    By addToCartBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    By addToCartBoltShirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    By addToCartFleeceJacket = By.id("add-to-cart-sauce-labs-fleece-jacket");
    By addToCartOnesie = By.id("add-to-cart-sauce-labs-onesie");
    By addToCartRedShirt = By.id("add-to-cart-test.allthethings()-t-shirt-(red)");

    By allProducts = By.className("inventory_item");
    By productName = By.className("inventory_item_name");
    By productPrice = By.className("inventory_item_price");
    By hamburgerMenu = By.id("react-burger-menu-btn");
    By logoutLink = By.id("logout_sidebar_link");
    By sortDropdown = By.className("product_sort_container");
    
    By pageTitle = By.className("title");
    By socialTwitterLink = By.className("social_twitter");
    By socialFacebookLink = By.className("social_facebook");
    By socialLinkedinLink = By.className("social_linkedin");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartBackpack).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(addToCartBikeLight).click();
    }

    public void addBoltShirtToCart() {
        driver.findElement(addToCartBoltShirt).click();
    }

    public void addFleeceJacketToCart() {
        driver.findElement(addToCartFleeceJacket).click();
    }

    public void addOnesieToCart() {
        driver.findElement(addToCartOnesie).click();
    }

    public void addRedShirtToCart() {
        driver.findElement(addToCartRedShirt).click();
    }

    public void addAllProductsToCart() {
        addBackpackToCart();
        addBikeLightToCart();
        addBoltShirtToCart();
        addFleeceJacketToCart();
        addOnesieToCart();
        addRedShirtToCart();
    }

    public int getTotalProductCount() {
        List<WebElement> products = driver.findElements(allProducts);
        return products.size();
    }

    public String getFirstProductName() {
        return driver.findElements(productName).get(0).getText();
    }

    public String getFirstProductPrice() {
        return driver.findElements(productPrice).get(0).getText();
    }

    public void openMenu() {
        driver.findElement(hamburgerMenu).click();
    }

    public void logout() {
        openMenu();
        driver.findElement(logoutLink).click();
    }

    public void sortByPriceLowToHigh() {
        WebElement dropdown = driver.findElement(sortDropdown);
        dropdown.click();
        driver.findElement(By.cssSelector("option[value='lohi']")).click();
    }

    public String getFirstProductPriceAfterSort() {
        return driver.findElements(productPrice).get(0).getText();
    }
    
    public String getPageTitleText() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isTwitterLinkDisplayed() {
        return driver.findElement(socialTwitterLink).isDisplayed();
    }

    public boolean isFacebookLinkDisplayed() {
        return driver.findElement(socialFacebookLink).isDisplayed();
    }

    public boolean isLinkedinLinkDisplayed() {
        return driver.findElement(socialLinkedinLink).isDisplayed();
    }
}