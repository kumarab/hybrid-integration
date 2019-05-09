

import oracle.cloud.paas.environment.EnvironmentDetails


class RuntimeEnv implements EnvironmentDetails {

    Properties properties = new Properties()
    String name = ""

    public RuntimeEnv(String file) {
        File propertiesFile = new File(file)
        propertiesFile.withInputStream {
            properties.load(it)
        }

        name="Custom:"+file
    }

    @Override
    boolean isIntegrated() {
        return properties.integrated == true
    }

    @Override
    String getName() {
        return name
    }


    @Override
    String getTasGsiBridgeEndPoint() {
        return properties.TasGsiBridgeEndPoint
    }

    @Override
    String getTasCentralRestEndPoint() {
        return properties.TasCentralRestEndPoint
    }

    @Override
    String getTasCentralUserName() {
        return properties.TasCentralUserName
    }

    @Override
    String getTasCentralUserPassword() {
        return properties.TasCentralUserPassword
    }

    @Override
    String getTasCentralCtl() {
        return properties.TasCentralCtl
    }

    @Override
    String getTasCentralHost() {
        return properties.TasCentralHost
    }

    @Override
    String getTasCentralSshPort() {
        return properties.TasCentralSshPort
    }

    @Override
    String getTasSshUser() {
        return properties.TasSshUser
    }

    @Override
    String getTasSshPassword() {
        return properties.TasSshPassword
    }

    @Override
    String getCimUrl() {
        return null
    }

    @Override
    String getCimUserName() {
        return null
    }

    @Override
    String getCimUserPassword() {
        return null
    }

    @Override
    String getDefaultRegion() {
        return properties.DefaultRegion
    }

    @Override
    String getTasDataCenterCtl() {
        return properties.TasDataCenterCtl
    }

    @Override
    String getTasDataCenterHost() {
        return properties.TasDataCenterHost
    }

    @Override
    String getTasDataCenterSshPort() {
        return properties.TasDataCenterSshPort
    }

    @Override
    String getCloudUIPassword() {
        return properties.TasCentralUserName
    }

    @Override
    String getCloudUIUser() {
        return properties.TasCentralUserPassword
    }

    @Override
    String getPsmRestApiEndPoint() {
        return properties.PsmRestApiEndPoint
    }

    @Override
    String getPsmUserName() {
        return properties.PsmUserName
    }

    @Override
    String getPsmUserPassword() {
        return properties.PsmUserPassword
    }
    @Override
    String getPsmHome() {
        return properties.PsmHome
    }

    @Override
    String getSmSitUrl() {
        if(properties.SmSitUrl == null)
            return "http://den01cnt.us.oracle.com:8081"
        return properties.SmSitUrl
    }

    @Override
    String getSmDomainHome() {
        return properties.SmDomainHome
    }

    @Override
    String getSmMWHome() {
        return properties.SmMWHome
    }

    @Override
    String getSmKitHome() {
        return properties.SmKitHome
    }

    @Override
    String getSshPsmHost() {
        return properties.SshPsmHost
    }

    @Override
    String getSshPsmPort() {
        return properties.SshPsmPort
    }

    @Override
    String getSshPsmUserName() {
        return properties.SshPsmUserName
    }

    @Override
    String getSshPsmPassword() {
        return properties.SshPsmPassword
    }

    @Override
    String getDataCenterDbUserName() {
        return properties.DataCenterDbUserName
    }

    @Override
    String getDataCenterDbUserPassword() {
        return properties.DataCenterDbUserPassword
    }
    String sdiSchemaName = properties.SdiSchemaName

    @Override
    String getDataCenterDbConnectString() {
        return properties.DataCenterDbConnectString
    }

    @Override
    String getCentralDbUserName() {
        return properties.CentralDbUserName
    }

    @Override
    String getCentralDbUserPassword() {
        return properties.CentralDbUserPassword
    }

    @Override
    String getCentralDbConnectString() {
        return properties.CentralDbConnectString
    }

    @Override
    String getIdmRestUrl() {
        return properties.IdmRestUrl
    }

    @Override
    String getIdmRestUserName() {
        return properties.IdmRestUserName
    }

    @Override
    String getIdmRestUserPassword() {
        return properties.IdmRestUserPassword
    }

    @Override
    String getEmHost() {
        return properties.EmHost
    }

    @Override
    String getEmRegistrationPassword() {
        return properties.EmRegistrationPassword
    }

    @Override
    String getEmUploadPort() {
        return properties.EmUploadPort
    }

    @Override
    String getEmccPassword() {
        return properties.EmccPassword
    }

    @Override
    String getEmccUsername() {
        return properties.EmccUsername
    }

    @Override
    String getEmManagedPort() {
        return properties.EmManagedPort
    }

    @Override
    String getEmProtocol() {
        return properties.EmProtocol
    }

    @Override
    String getEmccMonitorPassword() {
        return properties.EmccMonitorPassword
    }

    @Override
    String getAntiVirusHost() {
        return properties.AntiVirusHost
    }

    @Override
    String getAntiVirusPort() {
        return properties.AntiVirusPort
    }

    @Override
    String getNotificationURL() {
        return properties.NotificationURL
    }

    @Override
    String getNotificationUserName() {
        return properties.NotificationUserName
    }

    @Override
    String getNotificationUserPassword() {
        return properties.NotificationURLPassword
    }


    @Override
    String getMeteringURL() {
        return properties.MeteringURL
    }

    @Override
    String getMeteringUserName() {
        return properties.MeteringUserName
    }

    @Override
    String getMeteringPassword() {
        return properties.MeteringPassword
    }

    @Override
    String getIdStoreHost() {
        return properties.IdStoreHost
    }

    @Override
    String getIdStorePort() {
        return properties.IdStorePort
    }

    @Override
    String getPolicyStoreHost() {
        return properties.PolicyStoreHost
    }

    @Override
    String getPolicyStorePort() {
        return properties.PolicyStorePort
    }

    @Override
    String getOIMHost() {
        return properties.OIMHost
    }

    @Override
    String getOIMPort() {
        return properties.OIMPort
    }

    @Override
    String getTenantManagerCommand() {
        return properties.TenantManagerCommand
    }

    @Override
    String getOIMSshPort() {
        return properties.OIMSshPort
    }

    @Override
    String getIdmRegTemplate(String idmBlueprintFile) {
        return properties.IdmRegTemplate
    }


    @Override
    String getIdcsInfraClientId() {
        return properties.IdcsInfraClientId
    }

    @Override
    String getIdcsInfraClientSecret() {
        return properties.IdcsInfraClientSecret
    }

    @Override
    String getIdcsInfraTenantURL() {
        return properties.IdcsTenantUrl
    }

    @Override
    String getIdcsTenantURL(String tenantName) {
        return null
    }

    @Override
    String getParentVlbr() {
        return properties.ParentVlbr
    }

    @Override
    String getParentVlbrUsername() {
        return properties.ParentVlbrUsername
    }

    @Override
    String getParentVlbrPassword() {
        return properties.ParentVlbrPassword
    }

    @Override
    String getProxy() {
        return properties.proxy
    }

    @Override
    String getDnsServers() {
        return properties.dnsServers
    }

    @Override
    String getPcarDeploymentMode() {
        return properties.PcarDeploymentMode
    }

    @Override
    String getPsmSite() {
        return properties.PsmSite
    }

    String getHybridIdentityDomain(){
        return properties.HybridIdentityDomain
    }

    String getShellIdentityDomain(){
        return properties.ShellIdentityDomain
    }

    String getidentityDomainName(){
        return properties.identityDomainName
    }

    String getServiceType(){
        return properties.serviceType
    }

    String getServiceInstanceName(){
        return properties.serviceInstanceName
    }

    String getServicePODName(){
        return properties.servicePODName
    }
}
