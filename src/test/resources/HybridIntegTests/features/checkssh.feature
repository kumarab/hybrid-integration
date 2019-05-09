@integrationall
Feature: Verify sanity intergation tests
  This test should be run after provisioning

  @chekssh
  Scenario: CheckSSH
    Given SSH connection details for target POD VM
    When Try Establising  SSH connection to target POD VM
    Then SSH Connection should be successful
