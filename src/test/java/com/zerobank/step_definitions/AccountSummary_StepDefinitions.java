package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Then;
import org.apache.poi.hssf.record.chart.DatRecord;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AccountSummary_StepDefinitions {

    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();

    @Then("the title should be {string}")
    public void the_title_should_be(String expectedTitle) {

        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
    @Then("the title should be Zero – Account summary.")
    public void the_title_should_be_zero_account_summary() {

    }

    @Then("page should have to following:")
    public void page_should_have_to_following (List<String> expectedAccountTypes) {

        Assert.assertEquals(expectedAccountTypes, BrowserUtils.getElementsText(accountSummaryPage.actualAccountTypes));

    }

    @Then("Credit Accounts table must have columns")
    public void credit_accounts_table_must_have_columns(List<String> expectedColumnsUnderCreditAccounts) {

        Assert.assertEquals(expectedColumnsUnderCreditAccounts,BrowserUtils.getElementsText(accountSummaryPage.actualColumnsUnderCreditAccounts));
    }

}
