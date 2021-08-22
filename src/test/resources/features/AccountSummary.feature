Feature: Account summary page should have the title Zero – Account summary. Account summary page should have to following account types: Cash Accounts, Investment Accounts, Credit Accounts, Loan Accounts. Credit Accounts table must have columns Account, Credit Card and Balance.

  @smoke @regression
  Scenario: Account summary page should have the title verification
    Given user is on login page
    When user logs in with valid credentials "username" and "password"
    Then the title should be "Zero - Account Summary"
    Then page should have to following:
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |
    Then Credit Accounts table must have columns
      | Account     |
      | Credit Card |
      | Balance     |
