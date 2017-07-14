package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers}

class StartFunction extends CfFunction {

  @RugFunction(name = "cf-start", description = "Start CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "appName") appName: String): FunctionResponse = {
    operations().start(appName);
    FunctionResponse(Handlers.Status.Success)
  }
}
