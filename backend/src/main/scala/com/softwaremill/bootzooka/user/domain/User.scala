package com.softwaremill.bootzooka.user.domain

import java.time.OffsetDateTime
import java.util.UUID
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

import com.softwaremill.bootzooka.common.Utils
import com.softwaremill.bootzooka.user._

case class User(
  id: UserId,
  login: String,
  loginLowerCased: String,
  email: String,
  password: String,
  salt: String,
  createdOn: OffsetDateTime
)

object User {

  def withRandomUUID(
    login: String,
    email: String,
    plainPassword: String,
    salt: String,
    createdOn: OffsetDateTime
  ) = User(UUID.randomUUID(), login, login.toLowerCase, email, encryptPassword(plainPassword, salt), salt, createdOn)

  def encryptPassword(password: String, salt: String): String = {
    // 10k iterations takes about 10ms to encrypt a password on a 2013 MacBook
    val keySpec = new PBEKeySpec(password.toCharArray, salt.getBytes, 10000, 128)
    val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val bytes = secretKeyFactory.generateSecret(keySpec).getEncoded
    Utils.toHex(bytes)
  }

  def passwordsMatch(plainPassword: String, user: User): Boolean = {
    Utils.constantTimeEquals(
      user.password,
      encryptPassword(plainPassword, user.salt)
    )
  }
}

case class BasicUserData(id: UserId, login: String, email: String, createdOn: OffsetDateTime)

object BasicUserData {
  def fromUser(user: User) = new BasicUserData(user.id, user.login, user.email, user.createdOn)
}
