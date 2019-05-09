package HybridIntegTests;

import Hybrid.HybridAccounts;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import oracle.cloud.paas.logger.ProvisioningLogger;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class CreateAccount {
    private static Logger log = ProvisioningLogger.getLogger(CreateAccount.class);
    String stdOutAccount = "";
    @Given("Connect to TAS DB with credentials")
    public void tas() {

    }

    @When("run create account procedure")
    public void tas1() {
        try {
            stdOutAccount = HybridAccounts.createShellAccount("praveen.ramu@oracle.com", "PraveenRamu", "Testprell02", "29999993392", "RG001");
        }catch (Exception e){
            Assert.fail("Failed to create account " + e.getMessage());
        }
    }

    @Then("Check whether account is created or not")
    public void tas2(){
        if(!stdOutAccount.contains("Failed")){
            log.info("Account Creation Succeded.");
        } else {
            Assert.fail("Failed to create account.");
        }
    }



}