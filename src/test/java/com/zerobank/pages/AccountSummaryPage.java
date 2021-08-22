package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountSummaryPage {
    public AccountSummaryPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//h2[@class='board-header']")
    public List<WebElement> actualAccountTypes;

    @FindBy(xpath = "//th[.='Credit Card']/../th")
    public List<WebElement> actualColumnsUnderCreditAccounts;


}
