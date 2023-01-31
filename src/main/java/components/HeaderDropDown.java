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

    @FindBy (css = "[data-name='user-info'].js-header3-popup-trigger")
    private WebElement dropDownMenu;

    @FindBy (css = ".header3__user-info-popup-links a[href='/lk/biography/personal/']")
    private WebElement userProfileLink;


    public void moveToDropDownMenu() {
        moveToElement(dropDownMenu);
    }

    public void clickUserProfileLink() {
        clickElement(userProfileLink);
    }
}