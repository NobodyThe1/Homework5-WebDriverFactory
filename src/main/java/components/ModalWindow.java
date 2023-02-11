package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModalWindow extends AbsComponent implements IModalWindow {

    public ModalWindow (WebDriver driver) {
        super(driver);
    }

    private String modalSelector = ".modal-container[data-modal-id='new-log-reg']:not(.hide-top)";

    @FindBy (css = ".new-input-line_relative > button")
    private WebElement submitLoginButton;

    @Override
    public void popUpShouldNotBeVisible() {
        Assertions.assertTrue(waiter.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(modalSelector))));
    }

    @Override
    public void popUpShouldVisible() {
        Assertions.assertTrue(waiter.waitForCondition(ExpectedConditions.visibilityOf(
                driver.findElement(By.cssSelector(modalSelector))
        )));
    }

    public void clickSubmitLoginButton() {
        submit(submitLoginButton);
    }
}