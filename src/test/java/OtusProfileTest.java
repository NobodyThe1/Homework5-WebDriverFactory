import exceptions.BrowserNotSupportedException;
import factories.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.ProfilePage;

public class OtusProfileTest {

    private WebDriver driver;
    private String login = System.getProperty("login", "otus-test@mail.ru");
    private String password = System.getProperty("password", "1Testtest+");

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() throws BrowserNotSupportedException {
        driver = new WebDriverFactory().create("chrome");
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void otusProfileTest() {

        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.open();
        mainPage.getProfilePage(login, password);

        profilePage.fillPersonalData();
        profilePage.getSaveProfilePage().submit();

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        mainPage.open();
        mainPage.getProfilePage(login, password);

        profilePage.assertion();
    }
}