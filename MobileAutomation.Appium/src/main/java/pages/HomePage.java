package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pages.PageBase.clickButton;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "lanAndCountryTitle")
    WebElement country;

    @FindBy(id="action_apply")
    WebElement applyAction;

    public void selectCountry() {
        clickButton(country);
    }

    public void apply() {
        clickButton(applyAction);
    }
}
