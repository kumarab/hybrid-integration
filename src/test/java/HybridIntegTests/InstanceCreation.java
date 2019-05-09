package HybridIntegTests;


import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class InstanceCreation {


    @Given("Connect to PSM with provided credentials")
    public void testpsm()
    {
        System.out.println("Connect to PSM ");
    }

    @When("Launch PSM create Instance using REST")
    public void testpsm1(){
        System.out.println("Launch PSM create Instance using REST");
    }

    @Then("Instance should be created on PSM")
    public void testpsm2(){
        System.out.println("Instance should be created on PSM");
    }
}
