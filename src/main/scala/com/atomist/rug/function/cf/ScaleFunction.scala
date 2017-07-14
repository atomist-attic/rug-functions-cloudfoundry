package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers, JsonBodyOption}

class ScaleFunction extends CfFunction {

  @RugFunction(name = "cf-scale", description = "Scale CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "appName") appName: String,
         @Parameter(name = "instances") instances: Int): FunctionResponse = {
    operations().scale(appName, instances);
    FunctionResponse(Handlers.Status.Success)
  }
}
