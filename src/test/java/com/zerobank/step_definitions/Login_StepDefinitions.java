package com.zerobank.step_definitions;


import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user logs in with valid credentials {string} and {string}")
    public void userLogsInWithValidCredentialsAnd(String username, String password) {

        loginPage.login(username,password);

    }

    @Then("account summary page should be displayed")
    public void account_summary_page_should_be_displayed() {

        Assert.assertTrue(loginPage.accountSummaryTab.isDisplayed());
        Driver.closeDriver();

    }


    @When("user enters invalid {string} and or {string}")
    public void userEntersInvalidAndOr(String username, String password) {
        loginPage.login(username,password);
    }

    @Then("an error message Login and or password are wrong. should be displayed.")
    public void an_error_message_login_and_or_password_are_wrong_should_be_displayed() {
        String expectedErrorMessage = "Login and/or password are wrong.";
        String actualMessage = Driver.getDriver().findElement(By.xpath("//div[@class='alert alert-error']")).getText();

        Assert.assertEquals(expectedErrorMessage,actualMessage);
        Driver.closeDriver();
    }
}
