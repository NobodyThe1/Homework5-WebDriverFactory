import data.MessengersData;
import data.PhoneData;
import data.IdData;
import data.countries.CountryData;
import data.countries.cities.RussianCitiesData;
import components.InputForm;
import components.ModalWindow;
import exceptions.BrowserNotSupportedException;
import factories.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.ProfilePage;

import java.time.LocalDate;

public class OtusProfileTest {
    public OtusProfileTest() throws BrowserNotSupportedException {
    }

    private WebDriver driver = WebDriverFactory.create("chrome");
    private String login = System.getProperty("login", "otus-test@mail.ru");
    private String password = System.getProperty("password", "1Testtest+");

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
    ProfilePage profilePage = new ProfilePage(driver);

    @Test
    public void otusProfileTest() {

        loginToOtus();

        inputForm.fillUserPersonalData(IdData.FNAME, "Иван");
        inputForm.fillUserPersonalData(IdData.LNAME, "Иванов");
        inputForm.fillUserPersonalData(IdData.FNAME_LATIN, "Ivan");
        inputForm.fillUserPersonalData(IdData.LNAME_LATIN, "Ivanov");
        inputForm.fillUserPersonalData(IdData.BLOG_NAME, "Иван");
        inputForm.fillDateOfBirth(LocalDate.now().minusYears(30));
        inputForm.selectCity(RussianCitiesData.MOSCOW);
        inputForm.setEnglishLevel("Средний (Intermediate)");

        inputForm.fillContactForm1(MessengersData.TELEGRAM.getName(), PhoneData.PHONE1.getName());

        profilePage.submitSaveProfilePage();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        loginToOtus();

        inputForm.personalDataShouldBeSameAs(IdData.FNAME, "Иван");
        inputForm.personalDataShouldBeSameAs(IdData.LNAME, "Иванов");
        inputForm.personalDataShouldBeSameAs(IdData.FNAME_LATIN, "Ivan");
        inputForm.personalDataShouldBeSameAs(IdData.LNAME_LATIN, "Ivanov");
        inputForm.personalDataShouldBeSameAs(IdData.BLOG_NAME, "Иван");
        inputForm.dateOfBirthShouldBeSameAs(LocalDate.now().minusYears(30));
        inputForm.countryShouldBeSameAs(CountryData.RUSSIA.getName());
        inputForm.cityShouldBeSameAs(RussianCitiesData.MOSCOW.getName());
        inputForm.englishLevelShouldBeSameAs("Средний (Intermediate)");
        inputForm.contactFormShouldBeSameAs(PhoneData.PHONE1.getName(), IdData.CONTACT_1.getName());
        inputForm.contactFormShouldBeSameAs(PhoneData.PHONE2.getName(), IdData.CONTACT_2.getName());
    }

    public void loginToOtus() {
        mainPage.open();
        modalWindow.popUpShouldNotBeVisible();
        mainPage.clickMainLoginPageButton();
        modalWindow.popUpShouldVisible();
        inputForm.fillLoginForm(login);
        inputForm.fillPasswordForm(password);
        modalWindow.clickSubmitLoginButton();
        inputForm.moveToDropDownMenu();
        inputForm.clickUserProfileLink();
    }
}