@regression
Feature: zerobank login feature
  Only authorized users should be able to login to the application. When user logs in with valid credentials, Account summary page should be displayed.

  @smoke
  Scenario: authorized users should be able to login to the application
    Given user is on login page
    When user logs in with valid credentials "username" and "password"
    Then account summary page should be displayed

  Scenario Outline: Users with wrong username or wrong password should not be able to login. Users with blank username or password should also not be able to login. When user tries to login with invalid information, error message Login and/or password are wrong. should be displayed.
    Given user is on login page
    When user enters invalid "<username>" and or "<password>"
    Then an error message Login and or password are wrong. should be displayed.

    Examples:
      | username      | password      |
      | wrongusername | wrongpassword |
      |               |               |
      |               | password      |
      | username      |               |

