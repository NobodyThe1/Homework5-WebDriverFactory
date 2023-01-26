package factories;

import data.BrowserData;
import exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import java.util.Locale;

public class WebDriverFactory implements IWebDriverFactory {

    private static String browserName = System.getProperty("browser", "chrome").toUpperCase().trim();
    private static BrowserData browserData = BrowserData.valueOf(browserName);
    public static WebDriver create(String browserName) throws BrowserNotSupportedException {
        switch (browserData) {
            case CHROME:
            {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            default: {
                throw new BrowserNotSupportedException(browserName);
            }
        }
    }

    public static WebDriver create(String browserName, AbstractDriverOptions options) throws BrowserNotSupportedException {
        switch (BrowserData.valueOf(browserName.toLowerCase(Locale.ROOT))) {
            case CHROME ->
            {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) options);
            }
            default -> {
                throw new BrowserNotSupportedException(browserName);
            }
        }
    }
}