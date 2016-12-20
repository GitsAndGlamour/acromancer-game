package com.acromancer.acromancer.user.application

import java.util.UUID

import com.acromancer.acromancer.user._
import com.softwaremill.session.{MultiValueSessionSerializer, SessionSerializer}

import scala.util.Try

case class Session(userId: UserId)

object Session {
  implicit val serializer: SessionSerializer[Session, String] = new MultiValueSessionSerializer[Session](
    (t: Session) => Map("id" -> t.userId.toString),
    m => Try { Session(UUID.fromString(m("id"))) }
  )
}
