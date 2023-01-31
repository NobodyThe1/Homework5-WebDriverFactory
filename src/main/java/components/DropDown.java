package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public class DropDown extends AbsComponent {

    public DropDown (WebDriver driver) {
        super(driver);
    }

    private final String dropDownSelector = ".lk-cv-block__select-option";
    private final String messengerSelector = "[data-num] [data-selected-option-class] label ~ *:not(.hide) button";

    @FindBy (css = ".js-lk-cv-dependent-master")
    private WebElement chooseCountry;

    @FindBy (css = dropDownSelector + "[title='Россия']")
    private WebElement countryRussia;

    @FindBy (css = ".js-custom-select-input[name = 'city']~div")
    private WebElement chooseCity;

    @FindBy (css = dropDownSelector + "[title='Москва']")
    private WebElement cityMoscow;

    @FindBy (css = "[name='english_level'] ~ div")
    private WebElement englishLevel;

    @FindBy (css = dropDownSelector + "[title='Средний (Intermediate)']")
    private WebElement englishIntermediate;

    @FindBy (css = "input[name='contact-0-service']~div")
    private WebElement contactMenu1;

    @FindBy (css = "input[name='contact-1-service']~div")
    private WebElement contactMenu2;

    @FindBy (css = messengerSelector + "[title='WhatsApp']")
    private WebElement messengerWhatsApp;

    @FindBy (css = messengerSelector + "[data-value='telegram']")
    private WebElement messengerTelegram;

    @FindBy(css = ".lk-cv-block__select-options:not(.hide)")
    private WebElement selectOptionsNotHide;

    public void selectCountry() {
        selectItemFromDropDown(chooseCountry, countryRussia, selectOptionsNotHide);
    }
    public void selectCity() {
        selectItemFromDropDown(chooseCity, cityMoscow, selectOptionsNotHide);
    }

    public void setEnglishLevel() {
        selectItemFromDropDown(englishLevel, englishIntermediate, selectOptionsNotHide);
    }

    public void moveToContactMenu1() {
        moveAndClick(contactMenu1);
    }

    public void clickMessengerWhatsApp() {
        clickElement(messengerWhatsApp);
    }

    public void moveToContactMenu2() {
        moveAndClick(contactMenu2);
    }

    public void clickMessengerTelegram() {
        clickElement(messengerTelegram);
    }

    public void assertCountry() {
        assertByText("Россия", chooseCountry);
    }

    public void assertCity() {
        assertByText("Москва", chooseCity);
    }

    public void assertEnglishLevel() {
        assertByText("Средний (Intermediate)", englishLevel);
    }
}