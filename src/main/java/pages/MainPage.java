package pages;

import components.HeaderDropDown;
import components.InputForm;
import components.ModalWindow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public class MainPage extends AbsPage{
    public MainPage (WebDriver driver) {
        super(driver, "/");
    }

    public ModalWindow modalWindow = new ModalWindow(driver);
    public InputForm inputForm = new InputForm(driver);
    public HeaderDropDown headerDropDown = new HeaderDropDown(driver);
    public Waiter waiter = new Waiter(driver);

    @FindBy(css = ".header3__button-sign-in-container")
    private WebElement mainPageLoginButton;
    public WebElement getMainPageLoginButton() {
        return mainPageLoginButton;
    }

    public void getProfilePage(String login, String password) {
        getMainPageLoginButton().click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(modalWindow.getLoginWindow()));
        waiter.waitForCondition(ExpectedConditions.visibilityOf(inputForm.getLoginForm()));
        modalWindow.login(login, password);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(headerDropDown.getOpenDropDownMenu()));
        headerDropDown.moveToUserProfile();
    }
}