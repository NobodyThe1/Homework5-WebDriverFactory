import components.DropDown;
import components.HeaderDropDown;
import components.InputForm;
import components.ModalWindow;
import exceptions.BrowserNotSupportedException;
import factories.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.ProfilePage;

public class OtusProfileTest {

    private WebDriver driver = WebDriverFactory.create("chrome");
    private String login = System.getProperty("login", "otus-test@mail.ru");
    private String password = System.getProperty("password", "1Testtest+");

    public OtusProfileTest() throws BrowserNotSupportedException {
    }

    @BeforeAll
    public static void setUp() throws BrowserNotSupportedException {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    MainPage mainPage = new MainPage(driver);
    ModalWindow modalWindow = new ModalWindow(driver);
    InputForm inputForm = new InputForm(driver);
    DropDown dropDown = new DropDown(driver);
    ProfilePage profilePage = new ProfilePage(driver);
    HeaderDropDown headerDropDown = new HeaderDropDown(driver);

    @Test
    public void otusProfileTest() {

        loginToOtus();

        inputForm.fillForm("fname");
        inputForm.fillForm("lname");
        inputForm.fillForm("fname_latin");
        inputForm.fillForm("lname_latin");
        inputForm.fillForm("blog_name");
        inputForm.fillDateOfBirth();
        dropDown.selectCountry();
        dropDown.selectCity();
        dropDown.setEnglishLevel();

        dropDown.moveToContactMenu1();
        inputForm.getContactForm1();
        dropDown.clickMessengerWhatsApp();
        inputForm.fillContactForm1();
        profilePage.addNewMessenger();
        dropDown.moveToContactMenu2();
        dropDown.clickMessengerTelegram();
        inputForm.fillContactForm2();

        profilePage.submitSaveProfilePage();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        loginToOtus();

        inputForm.assertForm("fname");
        inputForm.assertForm("lname");
        inputForm.assertForm("fname_latin");
        inputForm.assertForm("lname_latin");
        inputForm.assertForm("blog_name");
        inputForm.assertDateOfBirth();
        dropDown.assertCountry();
        dropDown.assertCity();
        dropDown.assertEnglishLevel();
        inputForm.assertContactForm1();
        inputForm.assertContactForm2();
    }

    public void loginToOtus() {
        mainPage.open();
        //modalWindow.popUpNotVisible();
        mainPage.clickMainLoginPageButton();
        //modalWindow.popUpVisible();
        inputForm.fillLoginForm(login);
        inputForm.fillPasswordForm(password);
        modalWindow.clickSubmitLoginButton();
        headerDropDown.moveToDropDownMenu();
        headerDropDown.clickUserProfileLink();
    }
}