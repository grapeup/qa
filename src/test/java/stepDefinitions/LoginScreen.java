package stepDefinitions;

import cucumber.TestContext;
import dataProviders.JsonDataReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import pageObjects.LoginPage;

import java.io.IOException;



public class LoginScreen {

    private final JsonDataReader reader = new JsonDataReader();
    private final LoginPage loginPage;
    private final TestContext testContext;

    public LoginScreen(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Given("user navigates to the login page")
    public void userNavigatesToTheLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @And("^user (?:is|remains) logged out$")
    public void userIsLoggedOut() {
        Assert.assertTrue("User isn't logged out", loginPage.isUserLoggedOut());
    }

    @When("^user enters (valid|invalid|no) credentials$")
    public void userEntersCredentials(String credType) throws IOException, ParseException {
        String username = "", password = "";
        if (credType.equals("valid")) {
            username = reader.readJSONData("uname");
            password = reader.readJSONData("pwd");
        } else if (credType.equals("invalid")) {
            username = "invalid@email.com";
            password = "incorrect123pwd";
        }
        loginPage.enterUsernameAndPassword(username, password);
    }


    @And("user clicks on the login button")
    public void userClicksOnTheLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("user sees an error message")
    public void userSeesAnErrorMessage() {
        Assert.assertTrue("Error message wasn't displayed", loginPage.isErrorMessageDisplayed());
    }
}
