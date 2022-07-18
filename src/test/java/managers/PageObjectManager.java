package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import pageObjects.HomePage;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    public PageObjectManager(WebDriver driver) {

        this.driver = driver;

    }

    public HomePage getHomePage(){

        return (homePage == null) ? homePage = new HomePage(driver) : homePage;

    }



    public LoginPage getLoginPage() {

        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
        //return loginPage = new LoginPage(driver);

    }


}


