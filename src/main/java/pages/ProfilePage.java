package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends AbsPage {

    public ProfilePage (WebDriver driver) {
        super(driver, "/lk/biography/personal");
    }

    @FindBy (css = ".button_blue.lk-cv-action-buttons__button.js-disable-on-submit")
    private WebElement saveProfilePage;

    public void submitSaveProfilePage() {
        submit(saveProfilePage);
    }
}