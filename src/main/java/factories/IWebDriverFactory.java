package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;

public interface IWebDriverFactory {
    static WebDriver create(String webDriverName) {
        return null;
    }
    static WebDriver create(String webDriverName, AbstractDriverOptions options) {
        return null;
    }
}