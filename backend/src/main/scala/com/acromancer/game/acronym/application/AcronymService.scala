package com.acromancer.acromancer.user.application

import java.util.UUID

import com.acromancer.acromancer.acronym._
import com.acromancer.acromancer.acronym.application.AcronymDao
import com.acromancer.acromancer.acronym.domain.{Acronym, BasicAcronymData}

import scala.concurrent.{ExecutionContext, Future}

class AcronymService(acronymDao: AcronymDao)(implicit ec: ExecutionContext) {

  def findById(acronymId: AcronymId): Future[Option[BasicAcronymData]] = {
    acronymDao.findBasicDataById(acronymId)
  }

  def createNewAcronym(acronym: String, meaning: String): Future[AcronymCreationResult] = {
    def checkAcronymExistence(): Future[Either[String, Unit]] = {
      val existingAcronymFuture = acronymDao.findByAcronym(acronym)
      val existingMeaningFuture = acronymDao.findByMeaning(meaning)

      for {
        existingAcronymOpt <- existingAcronymFuture
        existingMeaningOpt <- existingAcronymFuture
      } yield {
        existingAcronymOpt.map(_ => Left("Acronym is already in use!")).orElse(
          existingMeaningOpt.map(_ => Left("Meaning is already in use!"))
        ).getOrElse(Right((): Unit))
      }
    }

    def creationValidData() = checkAcronymExistence().flatMap {
      case Left(msg) => Future.successful(AcronymCreationResult.AcronymExists(msg))
      case Right(_) =>
        val acronymAddCreation = acronymDao.add(Acronym.withRandomUUID(acronym.toUpperCase, meaning.toLowerCase))
        acronymAddCreation.map(_ => AcronymCreationResult.Success)
    }

    AcronymCreationValidator.validate(acronym, meaning).fold(
      msg => Future.successful(AcronymCreationResult.InvalidData(msg)),
      _ => creationValidData()
    )
  }

  def changeAcronym(acronymId: UUID, newAcronym: String): Future[Either[String, Unit]] = {
    acronymDao.findByAcronym(newAcronym).flatMap {
      case Some(_) => Future.successful(Left("Acronym is already taken"))
      case None => acronymDao.changeAcronym(acronymId, newAcronym).map(Right(_))
    }
  }

  def changeMeaning(acronymId: UUID, newMeaning: String): Future[Either[String, Unit]] = {
    acronymDao.findByMeaning(newMeaning).flatMap {
      case Some(_) => Future.successful(Left("Meaning used by another acronym"))
      case None => acronymDao.changeMeaning(acronymId, newMeaning).map(Right(_))
    }
  }
}

sealed trait AcronymCreationResult

object AcronymCreationResult {

  case class InvalidData(msg: String) extends AcronymCreationResult

  case class AcronymExists(msg: String) extends AcronymCreationResult

  case object Success extends AcronymCreationResult

}

object AcronymCreationValidator {
  private val ValidationOk = Right(())
  val MinAcronymLength = 2
  val MinSpaces = 1

  def validate(acronym: String, meaning: String): Either[String, Unit] =
    for {
      _ <- validAcronym(acronym.trim).right
      _ <- validMeaning(meaning.trim).right
    } yield ()

  private def validAcronym(acronym: String) = if (acronym.length >= MinAcronymLength) ValidationOk else Left("Acronym is too short!")

  private def validMeaning(meaning: String) = if (meaning.indexOf(' ') >= MinSpaces && meaning.nonEmpty) ValidationOk else Left("Invalid meaning!")
}

