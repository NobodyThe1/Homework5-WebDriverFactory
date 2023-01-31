package components;

import data.BrowserData;
import data.ProfileData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InputForm extends AbsComponent {

    public InputForm (WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[type='text'][name='email']:not(.hide)")
    private WebElement loginForm;

    @FindBy(css = ".new-input[name='password']")
    private WebElement passwordForm;

    @FindBy(css = "#id_fname")
    private WebElement firstNameForm;

    @FindBy(css = "#id_lname")
    private WebElement lastNameForm;

    @FindBy(css = "#id_fname_latin")
    private WebElement firstNameLatinForm;

    @FindBy(css = "#id_lname_latin")
    private WebElement lastNameLatinForm;

    @FindBy(css = "#id_blog_name")
    private WebElement blogNameForm;

    @FindBy(css = ".input[name='date_of_birth']")
    private WebElement dateOfBirthForm;

    @FindBy(css = "#id_contact-0-value")
    private WebElement contactForm1;

    @FindBy(css = "#id_contact-1-value")
    private WebElement contactForm2;


    public void fillLoginForm(String login) {
        sendKeys(loginForm, login);
    }

    public void fillPasswordForm(String password) {
        sendKeys(passwordForm, password);
    }

    public void fillForm(String selector) {
        ProfileData profileData = ProfileData.valueOf(selector.toUpperCase());
        switch (profileData) {
            case FNAME -> clearAndSendKeys(firstNameForm, "Иван");
            case LNAME -> clearAndSendKeys(lastNameForm, "Иванов");
            case FNAME_LATIN -> clearAndSendKeys(firstNameLatinForm, "Ivan");
            case LNAME_LATIN -> clearAndSendKeys(lastNameLatinForm, "Ivanov");
            case BLOG_NAME -> clearAndSendKeys(blogNameForm, "Иван");
        }
    }

    public void fillDateOfBirth() {
        clearAndSendKeys(dateOfBirthForm, "11.11.1991 \n");
    }

    public void getContactForm1() {
        waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#id_contact-0-value")));
        contactForm1.clear();
    }

    public void fillContactForm1() {
        sendKeys(contactForm1, "+7 999 123 45 67");
    }

    public void fillContactForm2() {
        waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#id_contact-1-value")));
        clearAndSendKeys(contactForm2, "+7 999 876 54 32");
    }

    public void assertForm(String selector) {
        ProfileData profileData = ProfileData.valueOf(selector.toUpperCase());
        switch (profileData) {
            case FNAME -> assertByAttribute("Иван", firstNameForm, "value");
            case LNAME -> assertByAttribute("Иванов", lastNameForm, "value");
            case FNAME_LATIN -> assertByAttribute("Ivan", firstNameLatinForm, "value");
            case LNAME_LATIN -> assertByAttribute("Ivanov", lastNameLatinForm, "value");
            case BLOG_NAME -> assertByAttribute("Иван", blogNameForm, "value");
        }
    }

    public void assertDateOfBirth() {
        assertByAttribute("11.11.1991", dateOfBirthForm, "value");
    }

    public void assertContactForm1() {
        waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.id("id_contact-0-value")));
        assertByAttribute("+7 999 876 54 32", contactForm1, "value");
    }

    public void assertContactForm2() {
        waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.id("id_contact-1-value")));
        assertByAttribute("+7 999 123 45 67", contactForm2, "value");
    }
}