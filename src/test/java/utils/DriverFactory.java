package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        if (browser == null) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();

                // Turn off the "Save password?" / "Change your password" popups
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);

                // Also disable the built-in password manager and related popups directly
                options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication");

                driver = new ChromeDriver(options);
                break;
        }

        return driver;
    }
}