package com.atomist.rug.function.cf

import com.atomist.rug.spi.annotation.{Parameter, RugFunction, Secret, Tag}
import com.atomist.rug.spi.{FunctionResponse, Handlers, JsonBodyOption}

class RecentLogsFunction extends CfFunction {

  @RugFunction(name = "cf-recentlogs", description = "Retrieve recent logs of a CloudFoundry application",
    tags = Array(new Tag(name = "cloudfoundry")))
  def run(@Parameter(name = "appName") appName: String,
          @Parameter(name = "organization") organization: String,
          @Parameter(name = "space") space: String,
          @Secret(name = "user", path = "secret://team/cloudfoundry/user") user: String,
          @Secret(name = "password", path = "secret://team/cloudfoundry/password") password: String) : FunctionResponse = {
    val log = operations(user, password, organization, space).recentLog(appName);
    FunctionResponse(Handlers.Status.Success, None, None, JsonBodyOption(log))
  }
}
