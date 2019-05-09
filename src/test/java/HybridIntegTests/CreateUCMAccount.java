package HybridIntegTests;

import Hybrid.UCMAccount;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import oracle.cloud.paas.logger.ProvisioningLogger;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CreateUCMAccount {
    private static Logger log = ProvisioningLogger.getLogger(CreateAccount.class);
    public String orderStatus = "";
    public String useremailid = "";
    public String Poolinfo = "Pool2";
    @Given("Required Payload and input data like username {string}")
    public void ucm01(String email){
        this.useremailid = email;
        log.info("UserEmailID :" + this.useremailid);
    }

    @When("Trigger UCM Account Creation")
    public void ucm02(){
        log.info("Creating UCM Account ..");
        UCMAccount.UCMAccountcreate(this.useremailid , Poolinfo);
    }

    @Then("Check whether UCM account is created or not" )
    public void ucm03(){
        if(orderStatus.isEmpty()){
            Assert.fail("Failed to create UCM account.");
        }else {
            log.info("Successfully create UCM Account");
        }

    }

}
