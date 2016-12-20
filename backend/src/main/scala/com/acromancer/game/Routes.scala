package com.acromancer.game

import akka.http.scaladsl.server.Directives._
import com.acromancer.game.common.api.RoutesRequestWrapper
import com.acromancer.game.passwordreset.api.PasswordResetRoutes
import com.acromancer.game.user.api.UsersRoutes
import com.acromancer.game.version.VersionRoutes

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
