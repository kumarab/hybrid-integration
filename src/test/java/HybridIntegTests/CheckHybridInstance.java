package HybridIntegTests;

import Hybrid.IntegrationBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckHybridInstance {
    @Given("Given Hybrid account creditials")
    public void checkServices() {

    }

    @When("Connect to PSM using Hybrid account and get all services")
    public void checkServices1() {
        IntegrationBase it =  new IntegrationBase();
        it.checkHybridInstance(it.env.getServiceInstanceName());

    }

    @Then("check service exists")
    public void checkServices2(){

    }
}
