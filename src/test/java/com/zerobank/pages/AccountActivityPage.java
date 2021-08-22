package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountActivityPage extends BasePage {
    public AccountActivityPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "aa_accountId")
    public WebElement accountsDropdown;

    @FindBy(xpath = "//table[@class='table table-condensed table-hover']//th")
    public List<WebElement> transactionColumns;

    @FindBy(linkText = "Find Transactions")
    public WebElement findTransactionsTab;

    @FindBy(id = "aa_fromDate")
    public WebElement fromDateBox;

    @FindBy(id = "aa_toDate")
    public WebElement toDateBox;

    @FindBy(xpath = "//button[.='Find']")
    public WebElement findButton;

    @FindBy(id = "aa_description")
    public WebElement descriptionBox;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//tbody//tr//td[1]")
    public List<WebElement> filteredTransactionsDates;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[2]")
    public List<WebElement> filteredDescriptions;
}
