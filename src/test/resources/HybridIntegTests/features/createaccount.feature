@createaccount
Feature: Create Account
  Create Account from Hybrid project

  @createshellaccount
  Scenario: Create shell account
    Given Connect to TAS DB with credentials
    When run create account procedure
    Then Check whether account is created or not

  @createshellaccountNegative
  Scenario: Create shell accountw
    Given Connect to TAS DB with invalid credentials
    When run create account procedure 1
    Then Check whether account is not created and appropriate error message is displayed.

