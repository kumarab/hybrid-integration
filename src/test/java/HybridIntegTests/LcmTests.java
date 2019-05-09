package HybridIntegTests;

import Hybrid.IntegrationBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import oracle.cloud.paas.logger.ProvisioningLogger;
import org.apache.logging.log4j.Logger;

public class LcmTests {
    public static String instanceName = "dummy";
    private static Logger log = ProvisioningLogger.getLogger(LcmTests.class);

    @Given("Collect PSM details like identity domain servicename")
    public void lcmCheck01() {
      //#TODO :load instanceName
    }

    @When("trigger {string} operation")
    public void lcmCheck02(String operation) {
        IntegrationBase it =  new IntegrationBase();
        log.info("OPERATION :" + operation);
        it.LCMPod(it.env.getServiceInstanceName(),operation);


    }

    @Then("operation should successful")
    public void lcmCheck03(){
        //lets sleep before doing next LCM operation
        try {
            Thread.sleep(1000 * 30);
        } catch (Exception e){

        }
    }
}
