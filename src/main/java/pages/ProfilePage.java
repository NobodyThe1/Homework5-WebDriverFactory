package pages;

import components.DropDown;
import components.InputForm;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public class ProfilePage extends AbsPage {

    public ProfilePage (WebDriver driver) {
        super(driver, "/lk/biography/personal");
    }

    @FindBy (css = ".js-lk-cv-custom-select-add")
    private WebElement newMessenger;

    @FindBy (css = ".button_blue.lk-cv-action-buttons__button.js-disable-on-submit")
    private WebElement saveProfilePage;


    public void addNewMessenger() {
        clickElement(newMessenger);
    }

    public void submitSaveProfilePage() {
        submit(saveProfilePage);
    }
}