package com.atomist.rug.function.cf

import com.atomist.rug.spi.AnnotatedRugFunction
import org.cloudfoundry.operations.DefaultCloudFoundryOperations
import org.cloudfoundry.reactor.DefaultConnectionContext
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider

class CfFunction extends AnnotatedRugFunction {

  def operations(user: String, password: String, organization: String, space: String): Operations = {
    val context = DefaultConnectionContext.builder.apiHost("api.run.pivotal.io").build
    val creds = PasswordGrantTokenProvider.builder.password(password).username(user).build
    val client = ReactorCloudFoundryClient.builder.connectionContext(context).tokenProvider(creds).build
    val doppler = ReactorDopplerClient.builder.connectionContext(context).tokenProvider(creds).build;
    new Operations(DefaultCloudFoundryOperations.builder.dopplerClient(doppler).cloudFoundryClient(client)
      .space(space).organization(organization).build)
  }

}
