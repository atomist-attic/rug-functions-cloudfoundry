package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers, Secret}

class ScaleFunction extends CfFunction {

  @RugFunction(name = "cf-scale", description = "Scale a CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "app") appName: String,
          @Parameter(name = "instances") instances: Int,
          @Parameter(name = "organization") organization: String,
          @Parameter(name = "space") space: String,
          @Secret(name = "user", path = "secret://team?path=cloudfoundry/user") user: String,
          @Secret(name = "password", path = "secret://team?path=cloudfoundry/password") password: String) : FunctionResponse = {
    operations(user, password, organization, space).scale(appName, instances);
    FunctionResponse(Handlers.Status.Success)
  }
}
