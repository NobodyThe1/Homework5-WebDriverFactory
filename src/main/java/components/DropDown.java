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

    Waiter waiter = new Waiter(driver);

    @FindBy (css = ".js-lk-cv-dependent-master")
    private WebElement chooseCountry;
    public WebElement getChooseCountry() {
        return chooseCountry;
    }

    @FindBy (css = dropDownSelector + "[title='Россия']")
    private WebElement countryRussia;
    public WebElement getCountryRussia() {
        return countryRussia;
    }

    @FindBy (css = ".js-custom-select-input[name = 'city']~div")
    private WebElement chooseCity;
    public WebElement getChooseCity() {
        return chooseCity;
    }

    @FindBy (css = dropDownSelector + "[title='Москва']")
    private WebElement cityMoscow;
    public WebElement getCityMoscow() {
        return cityMoscow;
    }

    @FindBy (css = "[name='english_level'] ~ div")
    private WebElement englishLevel;
    public WebElement getEnglishLevel() {
        return englishLevel;
    }

    @FindBy (css = dropDownSelector + "[title='Средний (Intermediate)']")
    private WebElement englishIntermediate;
    public WebElement getEnglishIntermediate() {
        return englishIntermediate;
    }

    @FindBy (css = "input[name='contact-0-service']~div")
    private WebElement contactService1;
    public WebElement getContactService1() {
        return contactService1;
    }

    @FindBy (css = "input[name='contact-1-service']~div")
    private WebElement contactService2;
    public WebElement getContactService2() {
        return contactService2;
    }

    @FindBy (css = messengerSelector + "[title='WhatsApp']")
    private WebElement messengerWhatsApp;
    public WebElement getMessengerWhatsapp() {
        return messengerWhatsApp;
    }

    @FindBy (css = messengerSelector + "[data-value='telegram']")
    private WebElement messengerTelegram;
    public WebElement getMessengerTelegram() {
        return messengerTelegram;
    }


    public void selectDropDownElement(WebElement dropDownList, WebElement elementToSelect) {
        actions.moveToElement(dropDownList).click().perform();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(elementToSelect));
        elementToSelect.click();
    }
}