package com.acromancer.acromancer

import akka.http.scaladsl.server.Directives._
import com.acromancer.acromancer.common.api.RoutesRequestWrapper
import com.acromancer.acromancer.passwordreset.api.PasswordResetRoutes
import com.acromancer.acromancer.user.api.UsersRoutes
import com.acromancer.acromancer.version.VersionRoutes

trait Routes extends RoutesRequestWrapper
    with UsersRoutes
    with PasswordResetRoutes
    with VersionRoutes {

  lazy val routes = requestWrapper {
    pathPrefix("api") {
      passwordResetRoutes ~
        usersRoutes ~
        versionRoutes
    } ~
      getFromResourceDirectory("webapp") ~
      path("") {
        getFromResource("webapp/index.html")
      }
  }
}
