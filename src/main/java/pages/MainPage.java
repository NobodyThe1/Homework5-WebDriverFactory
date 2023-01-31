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

    @FindBy(css = ".header3__button-sign-in-container")
    private WebElement mainPageLoginButton;

    public void clickMainLoginPageButton() {
        clickElement(mainPageLoginButton);
    }
}
