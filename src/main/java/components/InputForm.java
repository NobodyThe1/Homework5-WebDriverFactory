package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InputForm extends AbsComponent {

    public InputForm (WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[type='text'][name='email']:not(.hide)")
    private WebElement loginForm;
    public WebElement getLoginForm() {
        return loginForm;
    }

    @FindBy(css = ".new-input[name='password']")
    private WebElement passwordForm;
    public WebElement getPasswordForm() {
        return passwordForm;
    }

    @FindBy(css = "#id_fname")
    private WebElement firstNameForm;
    public WebElement getFirstNameForm() {
        return firstNameForm;
    }

    @FindBy(css = "#id_lname")
    private WebElement lastNameForm;
    public WebElement getLastNameForm() {
        return lastNameForm;
    }

    @FindBy(css = "#id_fname_latin")
    private WebElement firstNameLatinForm;
    public WebElement getFirstNameLatinForm() {
        return firstNameLatinForm;
    }

    @FindBy(css = "#id_lname_latin")
    private WebElement lastNameLatinForm;
    public WebElement getLastNameLatinForm() {
        return lastNameLatinForm;
    }

    @FindBy(css = "#id_blog_name")
    private WebElement blogNameForm;
    public WebElement getBlogNameForm() {
        return blogNameForm;
    }

    @FindBy(css = ".input[name='date_of_birth']")
    private WebElement dateOfBirthForm;
    public WebElement getDateOfBirthForm() {
        return dateOfBirthForm;
    }

    @FindBy(css = "#id_contact-0-value")
    private WebElement contactForm1;
    public WebElement getContactForm1() {
        return contactForm1;
    }

    @FindBy(css = "#id_contact-1-value")
    private WebElement contactForm2;
    public WebElement getContactForm2() {
        return contactForm2;
    }

    public void sendKeys(WebElement form, String keys) {
        form.sendKeys(keys);
    }

    public void clearAndSendKeys(WebElement form, String keys) {
        form.clear();
        form.sendKeys(keys);
    }
}