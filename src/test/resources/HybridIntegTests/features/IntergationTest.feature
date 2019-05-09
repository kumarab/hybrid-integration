@integrationall
Feature: Verify sanity intergation tests
  This test should be run after provisioning

  @chekssh
  Scenario: CheckSSH
    Given SSH connection details for target POD VM
    When Try Establising  SSH connection to target POD VM
    Then SSH Connection should be successful

  @checkinstanceexist
  Scenario: checkHybridInstance
    Given Given Hybrid account creditials
    When  Connect to PSM using Hybrid account and get all services
    Then  check service exists

  @lcmstop
  Scenario: StopInstance
    Given Collect PSM details like identity domain servicename
    When trigger "stop" operation
    Then operation should successful


  @lcmstart
  Scenario: StartInstance
    Given Collect PSM details like identity domain servicename
    When trigger "start" operation
    Then operation should successful

  @checkassociation
  Scenario: Check association
    Given POD and Service instance exists
    Then Check Service instance is associated to POD

  @checkAppinIDCS
  Scenario: Check App in IDCS application
    Given Service instance exists
    Then Check that application lists in IDCS admin Console


  @deleteServiceinstance
  Scenario: Delete service
    Given Service instance exist
    When Deletion of service instance is triggered
    Then POD ans service instance should get deleted

  @createServiceAgain
  Scenario: Create Service with the same old name
    Given Old POD and Service are deleted
    When Create instance and POD with the same name
    Then POD and service instance should get created