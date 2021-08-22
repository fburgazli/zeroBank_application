package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayBillsPage extends BasePage {

    public PayBillsPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "sp_account")
    public WebElement accountDropdown;

    @FindBy(id = "sp_amount")
    public WebElement amountBox;

    @FindBy(id = "sp_date")
    public WebElement dateBox;

    @FindBy(id = "sp_description")
    public WebElement descriptionBox;

    @FindBy(id = "pay_saved_payees")
    public WebElement payButton;

    /**Add New Payee*/
    @FindBy(linkText = "Add New Payee")
    public WebElement addNewPayeeTab;

    @FindBy(id = "np_new_payee_name")
    public WebElement inputNewPayeeName;

    @FindBy(id = "np_new_payee_address")
    public WebElement inputNewPayeeAddress;

    @FindBy(id = "np_new_payee_account")
    public WebElement inputPayeeAccount;

    @FindBy(id = "np_new_payee_details")
    public WebElement inputPayeeDetails;

    @FindBy(id = "add_new_payee")
    public  WebElement addNewPayeeButton;

    @FindBy(id="alert_content")
    public WebElement confirmation;

    /**Purchase Foreign Currency*/
    @FindBy(linkText = "Purchase Foreign Currency")
    public WebElement purchaseForeignCurrencyTab;

    @FindBy(id = "pc_currency")
    public  WebElement currencyDropdown;

    @FindBy(id = "purchase_cash")
    public WebElement purchaseButton;



}
