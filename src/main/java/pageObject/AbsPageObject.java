package pageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiter = new Waiter(driver);

        PageFactory.initElements(driver, this);
    }


    protected void moveToElement(WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }

    protected void moveAndClick(WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).click().perform();
    }

    protected void clickElement(WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    protected void submit(WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        element.submit();
    }

    protected void sendKeys(WebElement element, String keys) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    protected void clearAndSendKeys(WebElement element, String keys) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(keys);
    }

    protected void selectItemFromDropDown(WebElement dropDownList, WebElement elementToSelect, WebElement locator) {
        moveAndClick(dropDownList);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(locator));
        moveAndClick(elementToSelect);
        //waiter.waitForCondition(ExpectedConditions.invisibilityOf(locator));
    }

    protected void assertByAttribute(String input, WebElement form, String attribute) {
        Assertions.assertEquals(input, form.getAttribute(attribute));
    }

    protected void assertByText(String text, WebElement element) {
        Assertions.assertEquals(text, element.getText());
    }
}