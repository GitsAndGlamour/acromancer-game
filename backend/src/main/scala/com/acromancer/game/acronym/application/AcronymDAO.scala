package com.acromancer.acromancer.acronym.application

import java.time.OffsetDateTime
import java.util.UUID

import com.acromancer.acromancer.common.FutureHelpers._
import com.acromancer.acromancer.common.sql.SqlDatabase
import com.acromancer.acromancer.acronym._
import com.acromancer.acromancer.acronym.domain.{BasicAcronymData, Acronym}

import scala.concurrent.{ExecutionContext, Future}

class AcronymDao(protected val database: SqlDatabase)(implicit val ec: ExecutionContext) extends SqlAcronymSchema {

  import database._
  import database.driver.api._

  def add(acronym: Acronym): Future[Unit] = db.run(acronyms += acronym).mapToUnit

  def findById(acronymId: AcronymId): Future[Option[Acronym]] = findOneWhere(_.id === acronymId)

  def findBasicDataById(acronymId: AcronymId): Future[Option[BasicAcronymData]] =
    db.run(acronyms.filter(_.id === acronymId).map(_.basic).result.headOption)

  private def findOneWhere(condition: Acronyms => Rep[Boolean]) = db.run(acronyms.filter(condition).result.headOption)

  def findByAcronym(acronym: String): Future[Option[Acronym]] = findOneWhere(_.acronym.toLowerCase === acronym.toLowerCase)

  def findByMeaning(meaning: String): Future[Option[Acronym]] = findOneWhere(_.meaning.toLowerCase === meaning.toLowerCase)

  def findByAcronymOrMeaning(acronymOrMeaning: String): Future[Option[Acronym]] = {
    findByMeaning(acronymOrMeaning).flatMap(acronymOpt =>
      acronymOpt.map(acronym => Future.successful(Some(acronym))).getOrElse(findByAcronym(acronymOrMeaning)))
  }

  def changeAcronym(acronymId: AcronymId, newAcronym: String): Future[Unit] = {
    db.run(acronyms.filter(_.id === acronymId).map(_.acronym).update(newAcronym)).mapToUnit
  }

  def changeMeaning(acronymId: AcronymId, newMeaning: String): Future[Unit] = {
    db.run(acronyms.filter(_.id === acronymId).map(_.acronym).update(newMeaning)).mapToUnit
  }
}

/**
 * The schemas are in separate traits, so that if your DAO would require to access (e.g. join) multiple tables,
 * you can just mix in the necessary traits and have the `TableQuery` definitions available.
 */
trait SqlAcronymSchema {

  protected val database: SqlDatabase

  import database._
  import database.driver.api._

  protected val acronyms = TableQuery[Acronyms]

  protected class Acronyms(tag: Tag) extends Table[Acronym](tag, "acronyms") {
    // format: OFF
    def id              = column[UUID]("id", O.PrimaryKey)
    def acronym         = column[String]("acronym")
    def meaning         = column[String]("meaning")

    def * = (id, acronym, meaning) <> ((Acronym.apply _).tupled, Acronym.unapply)
    def basic = (id, acronym, meaning) <> ((BasicAcronymData.apply _).tupled, BasicAcronymData.unapply)
    // format: ON
  }

}
