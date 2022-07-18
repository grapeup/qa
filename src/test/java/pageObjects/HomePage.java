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

    // web element locators dictionary:

    @FindBy(xpath = "//h1[text() = 'Summary of Leads Received']")
    WebElement header;

    @FindBy(css = ".dropdown-toggle")
    WebElement menuButton;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;

    // re-usable methods:

    public boolean isHomeScreenDisplayed(){
        return Helpers.isPresent(header) && Helpers.isPresent(menuButton) && Helpers.isPresent(logoutButton);
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

}