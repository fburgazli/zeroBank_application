
@regression
Feature: page should have the title Zero – Pay Bills.When user completes a successful Pay operation,
  The payment was successfully submitted. should be displayed. When user tries to make a payment
  without entering the amount or date, Please fill out this field message! should be displayed.
  Amount field should not accept alphabetical or special characters. Date field should not accept alphabetical characters.
  NOTE: . For the date input field you can just use sendKeys. No need to click on the date navigator.

  @smoke
  Scenario: Account summary page should have the title verification
    Given user is on login page
    When user logs in with valid credentials "username" and "password"
    And user navigates to "Pay Bills" page
    Then the title should be "Zero - Pay Bills"

@wip
    Scenario: successful pay operation
      Given user is on Pay Bills page
      When user selects "Checking" account
      And user enters "50" and "2021-07-27"
      And user write a description(optional)
      And user clicks pay button
      Then user should see payments successful message

  Scenario: user tries to make a payment without entering the amount or date
    Given user is on Pay Bills page
    When user enters "" and "21-10-04"
    And user clicks pay button
    Then user should see Please fill out this field. message


