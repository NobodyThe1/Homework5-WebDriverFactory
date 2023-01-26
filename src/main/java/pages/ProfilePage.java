package pages;

import components.DropDown;
import components.InputForm;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public class ProfilePage extends AbsPage {

    public ProfilePage (WebDriver driver) {
        super(driver, "/lk/biography/personal");
    }

    public InputForm inputForm = new InputForm(driver);
    public DropDown dropDown = new DropDown(driver);
    public Waiter waiter = new Waiter(driver);

    @FindBy (css = ".js-lk-cv-custom-select-add")
    private WebElement addMessenger;
    public WebElement getAddMessenger() {
        return addMessenger;
    }

    @FindBy (css = ".button_blue.lk-cv-action-buttons__button.js-disable-on-submit")
    private WebElement saveProfilePage;
    public WebElement getSaveProfilePage() {
        return saveProfilePage;
    }


    public void fillContacts(WebElement dropDownList, WebElement elementToSelect,
                             WebElement formToFill, String keys) {
        actions.moveToElement(dropDownList).click().perform();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(formToFill));
        formToFill.clear();
        elementToSelect.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(formToFill));
        formToFill.sendKeys(keys);
    }

    public void fillPersonalData() {
        inputForm.clearAndSendKeys(inputForm.getFirstNameForm(), "Иван");
        inputForm.clearAndSendKeys(inputForm.getLastNameForm(), "Иванов");
        inputForm.clearAndSendKeys(inputForm.getBlogNameForm(), "Иван");
        inputForm.clearAndSendKeys(inputForm.getFirstNameLatinForm(), "Ivan");
        inputForm.clearAndSendKeys(inputForm.getLastNameLatinForm(), "Ivanov");
        inputForm.clearAndSendKeys(inputForm.getDateOfBirthForm(), "11.11.1991 \n");
        dropDown.selectDropDownElement(dropDown.getChooseCountry(), dropDown.getCountryRussia());
        dropDown.selectDropDownElement(dropDown.getChooseCity(), dropDown.getCityMoscow());
        dropDown.selectDropDownElement(dropDown.getEnglishLevel(), dropDown.getEnglishIntermediate());
        fillContacts(dropDown.getContactService1(), dropDown.getMessengerWhatsapp(),
                inputForm.getContactForm1(), "+7 999 123 45 67");
        addMessenger.click();
        fillContacts(dropDown.getContactService2(), dropDown.getMessengerTelegram(),
                inputForm.getContactForm2(), "+7 999 876 54 32");
    }

    public void assertion() {
        Assertions.assertEquals("Иван", inputForm.getFirstNameForm().getAttribute("value"));
        Assertions.assertEquals("Иванов", inputForm.getLastNameForm().getAttribute("value"));
        Assertions.assertEquals("Ivan", inputForm.getFirstNameLatinForm().getAttribute("value"));
        Assertions.assertEquals("Ivanov", inputForm.getLastNameLatinForm().getAttribute("value"));
        Assertions.assertEquals("Иван", inputForm.getBlogNameForm().getAttribute("value"));
        Assertions.assertEquals("11.11.1991", inputForm.getDateOfBirthForm().getAttribute("value"));
        Assertions.assertEquals("Россия", dropDown.getChooseCountry().getText());
        Assertions.assertEquals("Москва", dropDown.getChooseCity().getText());
        Assertions.assertEquals("Средний (Intermediate)", dropDown.getEnglishLevel().getText());
        Assertions.assertEquals("+7 999 876 54 32", inputForm.getContactForm1().getAttribute("value"));
        Assertions.assertEquals("+7 999 123 45 67", inputForm.getContactForm2().getAttribute("value"));
    }
}