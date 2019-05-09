@provcreate
Feature: Test Provisioning
  Check Provisioning successful or not

  @createpod
  Scenario: Create POD
    Given Connect to PSM
    When Launch PSM create REST
    Then POD should be create on PSM
#
#  @createinst
#  Scenario: Create Instance VM
#    Given Connect to PSM with provided credentials
#    When Launch PSM create Instance using REST
#    Then Instance should be created on PSM
#
#
#
#  @createinst3
#  Scenario: Create Instance VM
#    Given Connect to PSM with provided credentials
#    When Launch PSM create Instance using REST
#    Then Instance should be created on PSM