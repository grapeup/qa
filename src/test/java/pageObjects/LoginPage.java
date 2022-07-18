package pageObjects;


import helpers.Helpers;
import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver ) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    // web element locators dictionary:
    @FindBy(name = "username")
    WebElement emailInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(className = "button-submit")
    WebElement loginButton;

    @FindBy(className = "error")
    WebElement errorMessage;

    // re-usable methods:

    public void navigateToLoginPage(){
        String loginUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
        driver.get(loginUrl);
    }

    public void enterUsername(String username){
        emailInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton() {loginButton.click();}

    public boolean isUserLoggedOut(){
        return Helpers.isPresent(loginButton) && Helpers.isPresent(emailInput) && Helpers.isPresent(passwordInput);
    }

    public boolean isErrorMessageDisplayed(){
        return Helpers.isPresent(errorMessage);
    }
}
