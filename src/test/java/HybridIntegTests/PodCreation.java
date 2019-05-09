package HybridIntegTests;



import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class PodCreation {


    @Given("Connect to PSM")
    public void hello()
    {


        System.out.println("Connect to PSM ");
    }

    @When("Launch PSM create REST")
    public void hello1(){
        System.out.println("Launch PSM create REST");
    }

    @Then("POD should be create on PSM")
    public void hello2(){
        System.out.println("POD should be create on PSM");
    }
}
