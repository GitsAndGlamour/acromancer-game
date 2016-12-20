package com.acromancer.acromancer.test

import com.acromancer.acromancer.common.sql.SqlDatabase
import com.acromancer.acromancer.email.application.{DummyEmailService, EmailTemplatingEngine}
import com.acromancer.acromancer.user.application.{UserDao, UserService}
import com.acromancer.acromancer.user.domain.User
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.ExecutionContext

trait TestHelpersWithDb extends TestHelpers with ScalaFutures {

  lazy val emailService = new DummyEmailService()
  lazy val emailTemplatingEngine = new EmailTemplatingEngine
  lazy val userDao = new UserDao(sqlDatabase)
  lazy val userService = new UserService(userDao, emailService, emailTemplatingEngine)

  def sqlDatabase: SqlDatabase

  implicit lazy val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  def newRandomStoredUser(password: Option[String] = None): User = {
    val u = newRandomUser(password)
    userDao.add(u).futureValue
    u
  }
}
