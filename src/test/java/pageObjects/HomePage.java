package pageObjects;

import helpers.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "")
    WebElement header;

    @FindBy(css = "")
    WebElement menuButton;

    @FindBy(xpath = "")
    WebElement logoutButton;


    public boolean isHomeScreenDisplayed(){
        return Helpers.isVisible(header) && Helpers.isVisible(menuButton) && Helpers.isVisible(logoutButton);
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

}