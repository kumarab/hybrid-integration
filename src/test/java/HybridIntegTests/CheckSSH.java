package HybridIntegTests;

import Hybrid.IntegrationBase;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import oracle.cloud.paas.logger.ProvisioningLogger;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


//@CucumberOptions(tags = "@smoke")
//@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty" ,"html:/Users/praveenramu/Documents/WorkCode/hybrid-integration-cucm/target" ,
        "junit:/Users/praveenramu/Documents/WorkCode/hybrid-integration-cucm/target/cucumber.xml"})
public class CheckSSH {
   IntegrationBase it ;
    private static Logger log = ProvisioningLogger.getLogger(CheckSSH.class);
    @Given("SSH connection details for target POD VM")
    public void setup(){
           it = new IntegrationBase();
    }


    @When("Try Establising  SSH connection to target POD VM")
    public void checkssh(){
        it.SSHCheck(it.env.getGenericData("IPaddress"),"psmintg","Welcome1");

        System.out.println("Try Establishing  SSH connection to target POD VM ");
    }

    @Then("SSH Connection should be successful")
    public void validatessh() {
        log.info("POD should be create on PSM");
        Assert.assertTrue(it.ssh.isConnected(), "SSH into VM was not successful");
    }

}