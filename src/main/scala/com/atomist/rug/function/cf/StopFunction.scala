package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers}

class StopFunction extends CfFunction {

  @RugFunction(name = "cf-stop", description = "Stop CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "appName") appName: String): FunctionResponse = {
    operations().stop(appName);
    FunctionResponse(Handlers.Status.Success)
  }
}
