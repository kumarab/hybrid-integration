import Hybrid.IntegrationBase
import oracle.cloud.paas.environment.EnvironmentDetails
import spock.lang.Specification

class IntegTests extends Specification {
    def "CheckSSHConnect"() {
        given:"SSH connection details for target POD VM"
        def lib = new IntegrationBase()

        when: "Try Establising  SSH connection to target POD VM"
        def result = lib.SSHCheck(lib.ipddr,"psmintg","Welcome1")

        then:"SSH Connection should be successful"
        result == true
    }


}