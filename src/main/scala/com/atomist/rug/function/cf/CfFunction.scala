package com.atomist.rug.function.cf

import com.atomist.rug.spi.AnnotatedRugFunction
import org.cloudfoundry.operations.DefaultCloudFoundryOperations
import org.cloudfoundry.reactor.DefaultConnectionContext
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider

/**
  * Created by cdupuis on 14.07.2017.
  */
class CfFunction extends AnnotatedRugFunction {

  def operations(): Operations = {
    val context = DefaultConnectionContext.builder.apiHost("api.run.pivotal.io").build
    val creds = PasswordGrantTokenProvider.builder.password("TaL-B2e-YVb-Jep").username("cd@atomist.com").build
    val client = ReactorCloudFoundryClient.builder.connectionContext(context).tokenProvider(creds).build
    val doppler = ReactorDopplerClient.builder.connectionContext(context).tokenProvider(creds).build;
    new Operations(DefaultCloudFoundryOperations.builder.dopplerClient(doppler).cloudFoundryClient(client).space("development").organization("atomist").build)
  }

}
