Feature: Navigating to specific accounts in Accounts Activity

@regression
  Scenario Outline: Savings account redirect
    Given the user is logged in
    When the user clicks on "<clickedLink>" link on the Account Summary page
    Then the Account Activity page should be displayed
    And Account drop down should have "<selectedOption>" selected

    Examples:
      | clickedLink | selectedOption |
      | Savings     | Savings        |
      | Brokerage   | Brokerage      |
      | Checking    | Checking       |
      | Credit Card | Credit Card    |
      | Loan        | Loan           |