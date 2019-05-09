package Hybrid;

import io.restassured.RestAssured;
import oracle.cloud.paas.logger.ProvisioningLogger;
import oracle.cloud.paas.psm.connectors.IDCSConnector;
import oracle.cloud.paas.psm.models.ServiceAyncResponse;
import oracle.cloud.paas.psm.servicehandlers.PaasHandler;
import oracle.cloud.paas.psm.servicehandlers.StackHandler;
import oracle.cloud.paas.utils.SshUtil;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import javax.ws.rs.core.Response;


public class IntegrationBase {
    private static Logger log = ProvisioningLogger.getLogger(IntegrationBase.class);
    public static  SshUtil ssh= new SshUtil();
    public RuntimeEnv env= new RuntimeEnv(System.getProperty("user.dir")+"/env.properties");
    PaasHandler HybridPaasHandler = new PaasHandler(env,env.getPsmUserName(),env.getPsmUserPassword(),env.getHybridIdentityDomain(), env.getServiceType());
    PaasHandler CustomerPaasHandler = new PaasHandler(env,env.getPsmUserName(),env.getPsmUserPassword(),env.getShellIdentityDomain(), env.getServiceType());
    PaasHandler InfraPaasHandler = new PaasHandler(env,env.getPsmUserName(),env.getPsmUserPassword(),env.getidentityDomainName(), env.getServiceTypePOD());
    StackHandler InfraStackHandler = new StackHandler(env,env.getPsmUserName(),env.getPsmUserPassword(),env.getidentityDomainName());
    IDCSConnector IDCSconnector = new IDCSConnector(env.getClientID(),env.getClientSecret(),env.getIDCSAPIforApplication());
    public String createServiceJobID= null;
    public String inputDataSet = env.getInputData();

    public String CreatePayload ="{\n" +
            "    \"edition\": \"SUITE\",\n" +
            "    \"enableNotification\": \"true\",\n" +
            "    \"featureSet\": \"ics_pcs\",\n" +
            "    \"serviceVersion\": \"1.0\",\n" +
            "    \"isBYOL\": \"false\",\n" +
            "    \"serviceLevel\": \"PAAS\",\n" +
            "    \"managedSystemType\": \"oracle\",\n" +
            "    \"num5kMessagePack\": \"1\",\n" +
            "    \"serviceName\": " + "\"" + env.getServiceInstanceName()+ "\"" + ",\n" +
            "    \"targetTenant\":" + "\"" + env.getShellIdentityDomain() + "\"" + ",\n" +
            "    \"notificationEmail\":" + "\"" + env.getNotificationEmail() + "\"" +"}";

    public String createPODPayload = "{  \n" +
            "   \"loadBalancerSubnet1\":" + "\"" + env.getloadBalancerSubnet1() + "\"" + ",\n" +
            "   \"stackRegion\":{  \n" +
            "      \"region\":" + "\"" + env.getregion() + "\"" + ",\n" +
            "      \"availabilityDomain\":" + "\"" + env.getavailabilityDomain() + "\"" + ",\n" +
            "      \"regionType\":\"OCI\"\n" +
            "   },\n" +
            "   \"loadBalancerSubnet2\":" + "\"" + env.getloadBalancerSubnet2() + "\"" + ",\n" +
            "   \"databaseAdminPassword\":\"Ach1z0#d\",\n" +
            "   \"serviceSubnet\":" + "\"" + env.getserviceSubnet() + "\"" + ",\n" +
            "   \"stackName\":" + "\"" + env.getPODName() + "\"" + ",\n" +
            "   \"stackTemplate\":" + "\"" + env.getStackTemplate() + "\"" + ",\n" +
            "   \"name\":" + "\"" + env.getPODName() + "\"" + ",\n" +
            "   \"template\":" + "\"" + env.getStackTemplate() + "\"" + ",\n" +
            "   \"featureTag\":\"ics_pcs\",\n" +
            "   \"featureSet\":\"Integration and Process\",\n" +
            "   \"dbaasSubnet\":\"" + env.getdbaasSubnet() + "\"\n" +
            "}";


    public boolean SSHCheck(String hostname, String username , String password ){
        ssh.connect(hostname,username,password,22);
        if(ssh.isConnected()){
            return true;
        } else {
            return false;
        }

    }

    public void instanceAssociationCheck(String PODName, String InstanceName)
    {
        JSONObject json  = new JSONObject(InfraPaasHandler.getServiceFull(PODName));
        JSONArray associationsResponse = json.getJSONObject("allAssociations").getJSONArray("fromAssociations");
        String sourceServiceName = null;
            for (int i = 0; i < associationsResponse.length(); i++) {
                if (associationsResponse.getJSONObject(i).getString("sourceServiceType").equals("OICINST")) {
                     sourceServiceName = associationsResponse.getJSONObject(i).get("sourceServiceName").toString();
                }
            }
        Assert.assertEquals(sourceServiceName, InstanceName
        , "Expected value from response for sourceServiceName is " + sourceServiceName);
    }

    public void checkHybridInstance(String InstanceName){
        JSONObject json  = new JSONObject(HybridPaasHandler.getServices());
        //Should be able to launch the URL fromOracle internal account Provisioned PAAS instance
        log.info("Checking instance :" + InstanceName);
         JSONObject serJson = (JSONObject) json.get("services");
         try{
             serJson.get(InstanceName);
             log.info("Instance exists: " +InstanceName);

         } catch (Exception e){
            Assert.fail("Not found:" + e.getMessage());
         }

    }

    public void LCMPod(String instanceName, String action){
        HybridPaasHandler.serviceLifeCycle("{\n" +
                "  \"allServiceHosts\": \"true\"\n" +
                "}",instanceName,action);
    }

    public void LCMStartStop(String InstanceName){
        LCMPod(InstanceName, "Stop");
        try{
            Thread.sleep(1000*30);
        } catch(Exception e) {
            //
        }
        LCMPod(InstanceName, "Start");
    }

    public void deleteService(String InstanceName) {
        String payload ="{}";
        HybridPaasHandler.deleteService(InstanceName, payload);
        try{
            Thread.sleep(1000*60);
        } catch(Exception e) {
            //
        }
    }

    public boolean checkServiceExists(String ServiceName) {
        Boolean instanceStatus= HybridPaasHandler.isServiceExists(ServiceName);
        return instanceStatus;
    }


    public boolean checkPODExists(String PODName)
    {
        Boolean PODStatus = InfraPaasHandler.isServiceExists(env.getServicePODName());
        return PODStatus;

    }

    public void createService() {
        ServiceAyncResponse response = HybridPaasHandler.createService(CreatePayload, env.getServiceInstanceName());
        createServiceJobID = response.getJobId();
        System.out.println(createServiceJobID);
        String url = env.getPsmRestApiEndPointforLog() + "/" + env.getHybridIdentityDomain() + "/job/" + createServiceJobID;
        Boolean boolk = InfraPaasHandler.pollForSuccess(url,600000, 1000);
        System.out.println(boolk);
    }

    public void createPOD() {
        System.out.println(createPODPayload);
        ServiceAyncResponse response = InfraStackHandler.createService(createPODPayload);
        createServiceJobID = response.getJobId();
        System.out.println(createServiceJobID);
        String url = env.getPsmRestApiEndPointforLog() + "/" + env.getHybridIdentityDomain() + "/job/" + createServiceJobID;
        Boolean boolk = InfraPaasHandler.pollForSuccess(url,600000, 1000);
        System.out.println(boolk);
    }

    public void checkPODandServiceInstance() {
        Boolean bool= checkServiceExists(env.getServiceInstanceName());
        Boolean boo= checkPODExists(env.getPODName());
        Assert.assertTrue(boo, "POD does not exist");
        Assert.assertTrue(bool, "Service instance does not exist");
    }

    public Boolean IDCScheckforService() {
        String token = IDCSconnector.getAccessToken("grant_type=client_credentials&scope=urn:opc:idm:__myscopes__");
        String endpoint= "admin/v1/Apps/";
        RestAssured.baseURI=env.getIDCSAPIforApplication();
        String Response=given().header("Authorization", "Bearer" +" " + token).header("Content-Type","application/scim+json").when().log().all().get(endpoint).then().extract().response().body().asString();
        Boolean status = Response.contains(env.getServiceInstanceName());
        System.out.println(status);
        return status;

    }

    public static void close() {
        ssh.close();
    }


      public static void main(String[] args){
         IntegrationBase it =  new IntegrationBase();
          it.checkHybridInstance("OICinstUCM2602");
          it.LCMStartStop("OICinstUCM2602");
//          it.LCMPod("OICinstUCM2602", "Start");

    }
}
