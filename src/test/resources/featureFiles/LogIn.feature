@regression
@smoke
@atlas-137
@atlas-116

  Feature: Log in to Your Project account

    Background:
      Given user navigates to the login page
      Then user is logged out

    Scenario: Log in using valid credentials
      Given user enters valid credentials
      And user clicks on the login button
      Then home screen is displayed

    Scenario: Log in using invalid credentials
      Given user enters invalid credentials
      And user clicks on the login button
      Then user sees an error message
      And user remains logged out

    Scenario: Log in without credentials
      Given user enters no credentials
      And user clicks on the login button
      Then user remains logged out

