@createucmaccount
Feature: Create UCM Account
  Create UCM Account from Hybrid project

  @createsucmacct
  Scenario: Create UCM account
  Given Required Payload and input data like username "praveen.ramu@oracle.com"
  When Trigger UCM Account Creation
  Then Check whether UCM account is created or not