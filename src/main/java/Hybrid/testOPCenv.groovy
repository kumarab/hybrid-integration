package Hybrid

import oracle.cloud.paas.environment.Environment
import oracle.cloud.paas.environment.EnvironmentDetails




Map a = [:]
a.psmRestApiEndPoint = "test"
a.pcarDeploymentMode = "SSH"
EnvironmentDetails environmentDetails = Environment.getEnvironment(a)
System.out.println(environmentDetails.getPcarDeploymentMode())

println("Fixing the issue")