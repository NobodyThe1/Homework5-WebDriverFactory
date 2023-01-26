package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalWindow extends AbsComponent {

    public ModalWindow (WebDriver driver) {
        super(driver);
    }
    InputForm inputForm = new InputForm(driver);

    @FindBy (css = ".modal-container[data-modal-id='new-log-reg']:not(.hide-top)")
    private WebElement loginWindow;
    public WebElement getLoginWindow() {
        return loginWindow;
    }

    @FindBy (css = ".new-input-line_relative > button")
    private WebElement submitLoginButton;
    public WebElement getSubmitLoginButton() {
        return submitLoginButton;
    }

    public void login(String login, String password) {
        inputForm.getLoginForm().sendKeys(login);
        inputForm.getPasswordForm().sendKeys(password);
        getSubmitLoginButton().submit();
    }
}