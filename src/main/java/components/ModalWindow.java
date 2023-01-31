package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModalWindow extends AbsComponent implements IModalWindow {

    public ModalWindow (WebDriver driver) {
        super(driver);
    }

    @FindBy (css = ".modal-container[data-modal-id='new-log-reg']:not(.hide-top)")
    private WebElement loginWindow;

    @FindBy (css = ".new-input-line_relative > button")
    private WebElement submitLoginButton;

    @Override
    public void popUpNotVisible() {
        Assertions.assertFalse(loginWindow.isDisplayed());
    }

    @Override
    public void popUpVisible() {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(loginWindow));
        Assertions.assertTrue(loginWindow.isDisplayed());
    }

    public void clickSubmitLoginButton() {
        submit(submitLoginButton);
    }
}