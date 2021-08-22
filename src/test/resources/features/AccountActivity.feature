Feature: Account Activity page should have the title Zero – Account activity.
  In the Account drop down default option should be Savings. Account drop down should have the following options: Savings, Checking, Loan, Credit Card, Brokerage. Transactions table should have column names Date, Description, Deposit, Withdrawal.

@regression
  Scenario: Account summary page should have the title verification
    Given user is on login page
    When user logs in with valid credentials "username" and "password"
    And user navigates to "Account Activity" page
    Then the title should be "Zero - Account Activity"
    Then Account drop down default option should be Savings
    Then Account drop down should have to following options:
      | Savings     |
      | Checking    |
      | Savings     |
      | Loan        |
      | Credit Card |
      | Brokerage   |

    Then Transactions table table must have columns
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |