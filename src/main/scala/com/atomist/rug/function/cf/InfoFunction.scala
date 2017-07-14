package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers, JsonBodyOption}

class InfoFunction extends CfFunction {

  @RugFunction(name = "cf-info", description = "Get info of a CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "appName") appName: String): FunctionResponse = {
    val info = operations().info(appName);
    FunctionResponse(Handlers.Status.Success, None, None, JsonBodyOption(info))
  }
}
