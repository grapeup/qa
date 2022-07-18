package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObjects.HomePage;

public class HomeScreen {

    HomePage homePage;
    TestContext testContext;


    public HomeScreen(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    @Then("home screen is displayed")
    public void homeScreeIsDisplayed() {
        Assert.assertTrue("User isn't on the home screen", homePage.isHomeScreenDisplayed() );
    }
}
