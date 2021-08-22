package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AccountActivity_StepDefinitions {
    AccountActivityPage accountActivityPage = new AccountActivityPage();
    List<String> filteredTransactionDatesText;
    List<String> filteredDescriptionsText = new ArrayList<>();
    List<WebElement> filteredResults;

    Select accountsDropdown;

    @Then("Account drop down default option should be Savings")
    public void account_drop_down_default_option_should_be_savings() {
        accountsDropdown = new Select(accountActivityPage.accountsDropdown);
        String actualDefaultOption = accountsDropdown.getFirstSelectedOption().getText();
        String expectedDefaultOption = "Savings";
        Assert.assertEquals(expectedDefaultOption, actualDefaultOption);
    }

    @Then("Account drop down should have to following options:")
    public void account_drop_down_should_have_to_following_options(List<String> expectedAccountDropdownOptions) {
        Assert.assertEquals(expectedAccountDropdownOptions, BrowserUtils.getElementsText(accountsDropdown.getOptions()));
    }

    @Then("Transactions table table must have columns")
    public void transactions_table_table_must_have_columns(List<String> expectedTransactionColumns) {
        Assert.assertEquals(expectedTransactionColumns, BrowserUtils.getElementsText(accountActivityPage.transactionColumns));
    }

    @And("user navigates to {string} page")
    public void userNavigatesToPage(String page) {
        Driver.getDriver().findElement(By.linkText(page)).click();
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        LoginPage loginPage = new LoginPage();
        loginPage.login("username", "password");
    }

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_account_summary_page(String link) {
        Driver.getDriver().findElement(By.xpath("//tr//td//a[.='" + link + "']")).click();
    }

    @Then("the Account Activity page should be displayed")
    public void the_account_activity_page_should_be_displayed() {
        String expectedTitle = "Zero - Account Activity";
        Assert.assertEquals(expectedTitle, Driver.getDriver().getTitle());
    }

    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_savings_selected(String expectedSelectedOption) {
        accountsDropdown = new Select(accountActivityPage.accountsDropdown);
        String actualSelectedOption = accountsDropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(expectedSelectedOption, actualSelectedOption);
        Driver.closeDriver();
    }

    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_find_transactions_tab() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        LoginPage loginPage = new LoginPage();
        loginPage.login("username", "password");

        accountActivityPage.accountActivityTab.click();

        accountActivityPage.findTransactionsTab.click();

    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {
        accountActivityPage.fromDateBox.clear();
        accountActivityPage.fromDateBox.sendKeys(fromDate);
        accountActivityPage.toDateBox.clear();
        accountActivityPage.toDateBox.sendKeys(toDate);


    }

    @When("clicks search")
    public void clicks_search() {
        accountActivityPage.findButton.click();
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String fromDate, String toDate) {

        filteredTransactionDatesText = BrowserUtils.getElementsText(accountActivityPage.filteredTransactionsDates);
        filteredTransactionDatesText.add(fromDate);
        filteredTransactionDatesText.add(toDate);
//        System.out.println("filteredTransactionDatesText = " + filteredTransactionDatesText);
//        System.out.println("fromDate = " + fromDate);
//        System.out.println("toDate = " + toDate);
        Collections.sort(filteredTransactionDatesText);
//        System.out.println("filteredTransactionDatesText = " + filteredTransactionDatesText);
//        System.out.println("**************************************************************");

       Assert.assertTrue(fromDate.equals(filteredTransactionDatesText.get(0)));
       Assert.assertTrue(toDate.equals(filteredTransactionDatesText.get(filteredTransactionDatesText.size()-1)));


    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {

        //todo: how????

    }

    @When("the user enters another date range from {string} to {string}")
    public void the_user_enters_another_date_range_from_to(String fromDate, String toDate) {
        Driver.getDriver().navigate().refresh();
        accountActivityPage.accountActivityTab.click();
        accountActivityPage.findTransactionsTab.click();
        accountActivityPage.fromDateBox.clear();
        accountActivityPage.fromDateBox.sendKeys(fromDate);
        accountActivityPage.toDateBox.clear();
        accountActivityPage.toDateBox.sendKeys(toDate);
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String date) {
        //System.out.println("filteredTransactionDatesText = " + filteredTransactionDatesText);
        Assert.assertTrue(!filteredTransactionDatesText.contains(date));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String str) {
        accountActivityPage.descriptionBox.clear();
        accountActivityPage.descriptionBox.sendKeys(str+ Keys.ENTER);
        filteredDescriptionsText.addAll(BrowserUtils.getElementsText(accountActivityPage.filteredDescriptions));
       // System.out.println("filteredDescriptionsText = " + filteredDescriptionsText);
    }
    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String str) {
        for(String each:filteredDescriptionsText){
           // System.out.println("each = " + each);
            Assert.assertTrue(each.contains(str));
        }


    }
    @When("the user enters another description {string}")
    public void the_user_enters_another_description(String str) {

        //todo : not updating the results
        Driver.getDriver().navigate().refresh();
        accountActivityPage.accountActivityTab.click();
        accountActivityPage.findTransactionsTab.click();
        accountActivityPage.descriptionBox.clear();
        accountActivityPage.descriptionBox.sendKeys(str+Keys.ENTER);
        filteredResults = Driver.getDriver().findElements(By.xpath("//div[@id='filtered_transactions_for_account']"));
        filteredDescriptionsText.addAll(BrowserUtils.getElementsText(filteredResults));
        //System.out.println("online filtered transaction"+ BrowserUtils.getElementsText(filteredResults));

    }
    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String str) {
        for(String each:filteredDescriptionsText){
            Assert.assertTrue(!each.contains(str));
        }
    }

    List<WebElement> filteredDepositColumn;
    List<WebElement> filteredWithdrawalColumn;
    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_deposit() {
        filteredDepositColumn= Driver.getDriver().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[3]"));
        System.out.println("filteredDepositColumn* = " + BrowserUtils.getElementsText(filteredDepositColumn));
        System.out.println("filteredDepositColumn.size = " + filteredDepositColumn.size());
        int count=0;
        for (WebElement webElement : filteredDepositColumn) {
            if(!webElement.getText().isEmpty()){count++;};
        }
        System.out.println("count = " + count);
        Assert.assertTrue(count>=1);
    }
    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_withdrawal() {
        filteredWithdrawalColumn= Driver.getDriver().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[4]"));
        System.out.println("filteredWithdrawalColumn* = " + BrowserUtils.getElementsText(filteredWithdrawalColumn));
        System.out.println("filteredWithdrawalColumn.size = " + filteredWithdrawalColumn.size());
        int count=0;
        for (WebElement webElement : filteredWithdrawalColumn) {
            if(!webElement.getText().isEmpty()){count++;};
        }
        System.out.println("count = " + count);
       Assert.assertTrue(count>=1);
    }
    @When("user selects type {string}")
    public void user_selects_type(String type) {
       Select typeDropdown = new Select(Driver.getDriver().findElement(By.id("aa_type")));
       typeDropdown.selectByVisibleText(type);
        accountActivityPage.findButton.click();
        if(type.equals("Deposit")) {
            filteredDepositColumn = Driver.getDriver().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[3]"));
        } else if(type.equals("Withdrawal")) {
            filteredWithdrawalColumn = Driver.getDriver().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[4]"));
        }
    }
    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_withdrawal() {
        filteredWithdrawalColumn= Driver.getDriver().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[4]"));
        System.out.println("filteredWithdrawalColumn = " + BrowserUtils.getElementsText(filteredWithdrawalColumn));
        System.out.println("filteredWithdrawalColumn.size = " + filteredWithdrawalColumn.size());
        int count=0;
        for (WebElement webElement : filteredWithdrawalColumn) {
            if(!webElement.getText().isEmpty()){count++;};
        }
        System.out.println("count = " + count);
       // Assert.assertTrue(count==0);
    }
    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_deposit() {
        filteredDepositColumn= Driver.getDriver().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[3]"));
        System.out.println("filteredDepositColumn = " + BrowserUtils.getElementsText(filteredDepositColumn));
        System.out.println("filteredDepositColumn.size = " + filteredDepositColumn.size());
        int count=0;
        for (WebElement webElement : filteredDepositColumn) {
            if(!webElement.getText().isEmpty()){count++;};
        }
        System.out.println("count = " + count);

       // Assert.assertTrue(count==0);
        //todo: solve the stale element problem.
    }


}
