package components;

import data.IdData;
import data.countries.ICityData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputForm extends AbsComponent {

    public InputForm (WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[type='text'][name='email']:not(.hide)")
    private WebElement loginForm;

    @FindBy(css = ".new-input[name='password']")
    private WebElement passwordForm;

    @FindBy(css = ".input[name='date_of_birth']")
    private WebElement dateOfBirthForm;

    @FindBy (css = "[name='english_level'] ~ div")
    private WebElement englishLevel;

    @FindBy(css = ".lk-cv-block__select-options:not(.hide)")
    private WebElement selectOptionsNotHide;

    @FindBy (css = "[data-name='user-info'].js-header3-popup-trigger")
    private WebElement dropDownMenu;

    @FindBy (css = ".header3__user-info-popup-links a[href='/lk/biography/personal/']")
    private WebElement userProfileLink;

    @FindBy (css = ".js-lk-cv-custom-select-add")
    private WebElement newMessenger;

    private String userPersonalData = "#id_%s";
    private final String dropDownSelector = ".lk-cv-block__select-option[title='%s']";
    private final String messengerSelector = "[data-num] [data-selected-option-class] label ~ *:not(.hide) button[data-value='%s']";
    private String contactMenu = "input[name='contact-%s-service']~div";
    private String countrySelector = ".lk-cv-block__select-option[title='%s']";
    private String citySelector = ".js-lk-cv-dependent-slave-city:not(data-slave-selector)";


    public void fillLoginForm(String login) {
        sendKeys(loginForm, login);
    }

    public void fillPasswordForm(String password) {
        sendKeys(passwordForm, password);
    }

    public void fillUserPersonalData (IdData profileData, String data) {
        clearAndSendKeys(findElementWithStringFormat(userPersonalData, profileData.getName()), data);
    }

    public void fillDateOfBirth(LocalDate birthDate) {
        clearAndSendKeys(dateOfBirthForm, birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy\n")));
    }

    public void fillContactForm1(String messenger, String phone) {
        moveAndClick(findElementWithStringFormat(contactMenu, "0"));
        waiter.waitForCondition(ExpectedConditions
                .presenceOfElementLocated(selectElementWithStringFormat(userPersonalData, IdData.CONTACT_1.getName())));
        findElementWithStringFormat(userPersonalData, IdData.CONTACT_1.getName()).clear();
        clickElement(findElementWithStringFormat(messengerSelector, messenger));
        sendKeys(findElementWithStringFormat(userPersonalData, IdData.CONTACT_1.getName()), phone);
    }

    public void fillContactForm2 (String messenger, String phone) {
        if (findElementWithStringFormat(userPersonalData, IdData.CONTACT_2.getName()).isDisplayed()) {
            clearAndSendKeys(findElementWithStringFormat(userPersonalData, IdData.CONTACT_2.getName()), phone);
        } else {
            clickElement(newMessenger);
            moveAndClick(findElementWithStringFormat(contactMenu, "1"));
            waiter.waitForCondition(ExpectedConditions
                    .presenceOfElementLocated(selectElementWithStringFormat(userPersonalData, IdData.CONTACT_2.getName())));
            findElementWithStringFormat(userPersonalData, IdData.CONTACT_2.getName()).clear();
            clickElement(findElementWithStringFormat(messengerSelector, messenger));
            sendKeys(findElementWithStringFormat(userPersonalData, IdData.CONTACT_2.getName()), phone);
        }
    }

    public void selectCity(ICityData city) {
        clickElement(driver.findElement(By.cssSelector("[data-slave-selector='.js-lk-cv-dependent-slave-city']")));
        clickElement(findElementWithStringFormat(countrySelector, city.getCountryData().getName()));
        waiter.waitForCondition(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[name='city']:not(disabled)"))));
        moveAndClick(driver.findElement(By.cssSelector(citySelector)));
        clickElement(findElementWithStringFormat(countrySelector, city.getName()));
    }

    public void setEnglishLevel(String level) {
        selectItemFromDropDown(englishLevel, findElementWithStringFormat(".lk-cv-block__select-option[title='%s']", level),
                selectOptionsNotHide);
    }


    public void countryShouldBeSameAs(String userCountry) {
        assertByText(userCountry, driver.findElement(By.cssSelector(".js-lk-cv-dependent-master")));
    }

    public void cityShouldBeSameAs(String userCity) {
        assertByText(userCity, driver.findElement(By.cssSelector(".js-custom-select-input[name = 'city']~div")));
    }

    public void englishLevelShouldBeSameAs(String level) {
        assertByText(level, englishLevel);
    }

    public void moveToDropDownMenu() {
        moveToElement(dropDownMenu);
    }

    public void clickUserProfileLink() {
        clickElement(userProfileLink);
    }

    public void personalDataShouldBeSameAs(IdData profileData, String expectedData) {
        String selector = String.format(userPersonalData, profileData.getName());
        Assertions.assertEquals(expectedData, driver.findElement(By.cssSelector(selector)).getAttribute("value"));
    }

    public void dateOfBirthShouldBeSameAs(LocalDate expectedDate) {
        Assertions.assertEquals(dateOfBirthForm.getAttribute("value"), expectedDate
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString());
    }

    public void contactFormShouldBeSameAs(String phone, String idData) {
        waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(selectElementWithStringFormat(userPersonalData, idData)));
        assertByAttribute(phone, findElementWithStringFormat(userPersonalData, idData), "value");
    }
}