package HybridIntegTests;

import Hybrid.IntegrationBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


public class LCMOperation {
    IntegrationBase obj =  new IntegrationBase();

    @Given("POD and Service instance exists")
    public void checkPODandService() {
        Boolean instanceStatus= obj.checkServiceExists(obj.env.getServiceInstanceName());
        Boolean PODStatus= obj.checkPODExists(obj.env.getServicePODName());
        Assert.assertTrue(PODStatus, "POD does not exists");
        Assert.assertTrue(instanceStatus, "Instance does not exists");
        System.out.println("tets");
    }

    @Then("Check Service instance is associated to POD")
    public void checkAssociation() {
        obj.instanceAssociationCheck(obj.env.getServicePODName(), obj.env.getServiceInstanceName());

    }


    @Given("Service instance exist")
    public void checkInstanceService() {
        obj.checkHybridInstance(obj.env.getServiceInstanceName());
        Boolean boo= obj.checkPODExists(obj.env.getPODName());
        Assert.assertTrue(boo, "Instance does not exists");
    }

    @When("Deletion of service instance is triggered")
    public void deleteServiceInstance()
    {
        obj.deleteService(obj.env.getServiceInstanceName());
    }

    @Then("POD ans service instance should get deleted")
    public void checkPODandServiceInstance() {
        obj.checkPODandServiceInstance();
    }

    @Given("Old POD and Service are deleted")
    public void checkInstanceServiceandPOD() {
      obj.checkPODandServiceInstance();
    }

    @When("Create instance and POD with the same name")
    public void createServiceInstance() {
        obj.createService();
       // obj.createPOD();
    }


    @Then("POD and service instance should get created")
    public void checkInstanceService3() {
        Boolean boo2 = obj.checkServiceExists(obj.env.getServiceInstanceName());
        Boolean boo= obj.checkPODExists(obj.env.getPODName());
        Assert.assertTrue(boo, "Instance with this name does not exist");
        Assert.assertTrue(boo2, "Instance does not exists");
    }


    @Then("Check that application lists in IDCS admin Console")
    public void checkThatApplicationListsInIDCSAdminConsole() {
        Boolean b = obj.IDCScheckforService();
        Assert.assertTrue(b, "This application in IDCS does not exist");
    }

    @Given("Service instance exists")
    public void serviceInstanceExists()
        {
            obj.checkServiceExists(obj.env.getServiceInstanceName());
        }


}