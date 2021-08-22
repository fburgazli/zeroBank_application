package com.zerobank.step_definitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.math3.analysis.function.Add;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PayBills_StepDefinitions {
    PayBillsPage payBillsPage = new PayBillsPage();
    Select accountsDropdown;

    @Given("user is on Pay Bills page")
    public void user_is_on_pay_bills_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login("username","password");
        payBillsPage.payBillsTab.click();
    }


    @When("user selects {string} account")
    public void user_selects_account(String accountType) {
        accountsDropdown = new Select(payBillsPage.accountDropdown);
        accountsDropdown.selectByVisibleText(accountType);
    }
    @When("user enters {string} and {string}")
    public void user_enters_and(String amount, String date) {
        for (int i = 0; i < amount.length(); i++) {
            if (!Character.isDigit(amount.charAt(i))) {
                System.out.println("Amount field should not accept alphabetical or special characters");
            }
        }
                if(amount.isEmpty()){
                    payBillsPage.amountBox.sendKeys("");
                }else {
                    payBillsPage.amountBox.sendKeys(Integer.parseInt(amount) + "");
                }



        for (int i = 0; i < date.length(); i++) {
            if (Character.isLetter(date.charAt(i))) {
                System.out.println("Date field should not accept alphabetical characters.");
            }
        }
                payBillsPage.dateBox.sendKeys(date);




    }
    @When("user write a description\\(optional)")
    public void user_write_a_description_optional() {
        payBillsPage.descriptionBox.sendKeys("cricket statement");

    }
    @When("user clicks pay button")
    public void user_clicks_pay_button() {
        payBillsPage.payButton.click();

    }
    @Then("user should see payments successful message")
    public void user_should_see_payments_successful_message() {
       String expectedMessage ="The payment was successfully submitted.";
       String actualMessage = Driver.getDriver().findElement(By.xpath("//span[.='The payment was successfully submitted.']")).getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Then("user should see Please fill out this field. message")
    public void userShouldSeePleaseFillOutThisFieldMessageMessage() {


        String expectedText = "Please fill out this field.";
        String alertMessage ="";
        alertMessage += payBillsPage.amountBox.getAttribute("validationMessage");
        alertMessage += payBillsPage.dateBox.getAttribute("validationMessage");
        alertMessage = alertMessage.trim();

        Assert.assertEquals(expectedText,alertMessage);

    }

    /**Add New Payee*/

    LoginPage loginPage= new LoginPage();
    @Given("user is on Add New Payee tab")
    public void user_is_on_add_new_payee_tab() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login("username","password");
        payBillsPage.payBillsTab.click();
        payBillsPage.addNewPayeeTab.click();
    }
    @Given("user creates a new payee using following information")
    public void user_creates_a_new_payee_using_following_information(Map<String,String> info) {
        payBillsPage.inputNewPayeeName.sendKeys(info.get("Payee Name"));
        payBillsPage.inputNewPayeeAddress.sendKeys(info.get("Payee Address"));
        payBillsPage.inputPayeeAccount.sendKeys(info.get("Account"));
        payBillsPage.inputPayeeDetails.sendKeys(info.get("Payee details"));
        payBillsPage.addNewPayeeButton.click();
    }
    @Then("message {string} should be displayed")
    public void message_the_new_payee_should_be_displayed(String expectedMessage) {
        String confirmation = payBillsPage.confirmation.getText();
        Assert.assertEquals("Confirmation message is not displayed!",expectedMessage,confirmation);

    }

    /**Purchase Foreign Currency*/

    @Given("the user accesses the Purchase foreign currency cash tab")
    public void the_user_accesses_the_purchase_foreign_currency_cash_tab() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login("username","password");
        payBillsPage.payBillsTab.click();
        payBillsPage.purchaseForeignCurrencyTab.click();
    }


    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> expectedDropdownMenu) {
        Select currencyDropdown = new Select(payBillsPage.currencyDropdown);
        List<WebElement> currencyList= currencyDropdown.getOptions();
        List<String> actualCurrencyMenu = new ArrayList<>(BrowserUtils.getElementsText(currencyList));
        actualCurrencyMenu.remove("Select One");
        Assert.assertEquals("Currency dropdown is not matching!",expectedDropdownMenu,actualCurrencyMenu);

    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        payBillsPage.purchaseButton.click();
    }
    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String errorMessage = alert.getText();
        System.out.println("errorMessage = " + errorMessage);
        alert.accept();
        Assert.assertTrue("Error alert not displayed!",!errorMessage.isEmpty());

    }

    @When("user tries to calculate cost without entering a value")
    public void user_tries_to_calculate_cost_without_entering_a_value() {
        Select currencyDropdown = new Select(payBillsPage.currencyDropdown);
        currencyDropdown.selectByVisibleText("Thailand (baht)");
        payBillsPage.purchaseButton.click();
    }






}
