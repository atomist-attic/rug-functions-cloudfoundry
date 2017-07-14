package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Secret, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers}

class StartFunction extends CfFunction {

  @RugFunction(name = "cf-start", description = "Start a CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "app") appName: String,
          @Parameter(name = "organization") organization: String,
          @Parameter(name = "space") space: String,
          @Secret(name = "user", path = "secret://team?path=cloudfoundry/user") user: String,
          @Secret(name = "password", path = "secret://team?path=cloudfoundry/password") password: String) : FunctionResponse = {
    operations(user, password, organization, space).start(appName)
    FunctionResponse(Handlers.Status.Success)
  }
}
