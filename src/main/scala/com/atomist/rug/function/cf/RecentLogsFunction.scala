package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers, JsonBodyOption}

class RecentLogsFunction extends CfFunction {

  @RugFunction(name = "cf-recentlogs", description = "Retrieve recent logs of a CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "appName") appName: String): FunctionResponse = {
    val log = operations().recentLog(appName);
    FunctionResponse(Handlers.Status.Success, None, None, JsonBodyOption(log))
  }
}
