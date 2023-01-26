package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public class HeaderDropDown extends AbsComponent {

    public HeaderDropDown (WebDriver driver) {
        super(driver);
    }

    Waiter waiter = new Waiter(driver);

    @FindBy (css = "[data-name='user-info'].js-header3-popup-trigger")
    private WebElement openDropDownMenu;
    public WebElement getOpenDropDownMenu() {
        return openDropDownMenu;
    }

    @FindBy (css = ".header3__user-info-popup-links a[href='/lk/biography/personal/']")
    private WebElement userProfileLink;
    public WebElement getUserProfileLink() {
        return userProfileLink;
    }

    public void moveToUserProfile() {
        actions.moveToElement(getOpenDropDownMenu()).perform();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(getUserProfileLink()));
        getUserProfileLink().click();
    }
}