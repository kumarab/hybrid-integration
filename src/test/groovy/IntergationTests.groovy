import Hybrid.IntegrationBase
import spock.lang.Specification

class IntergationTests extends Specification {
    def "CheckSSHConnect"() {
        given:"SSH connection details for target POD VM"
        def lib = new IntegrationBase()

        when: "Try Establising  SSH connection to target POD VM"
        def result = lib.SSHCheck("132.145.143.211","psmintg","Welcome1")

        then:"SSH Connection should be successful"
        result == true
    }
}