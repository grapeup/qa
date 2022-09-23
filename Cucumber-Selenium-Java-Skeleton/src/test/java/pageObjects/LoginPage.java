package pageObjects;


import helpers.Helpers;
import managers.FileReaderManager;
import org.openqa.selenium.Keys;
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


    @FindBy(name = "")
    WebElement emailInput;

    @FindBy(name = "")
    WebElement passwordInput;

    @FindBy(className = "")
    WebElement loginButton;

    @FindBy(className = "")
    WebElement errorMessage;


    public void navigateToLoginPage(){
        String loginUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
        driver.get(loginUrl);
    }
    

    public void clickOnLoginButton() {loginButton.click();}

    public boolean isUserLoggedOut(){
        return Helpers.isVisible(loginButton) && Helpers.isVisible(emailInput) && Helpers.isVisible(passwordInput);
    }

    public boolean isErrorMessageDisplayed(){
        return Helpers.isVisible(errorMessage);
    }

    public void enterUsernameAndPassword(String username, String password) {
        emailInput.click();
        emailInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        emailInput.sendKeys(username);
        passwordInput.click();
        passwordInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        passwordInput.sendKeys(password);
    }
}
