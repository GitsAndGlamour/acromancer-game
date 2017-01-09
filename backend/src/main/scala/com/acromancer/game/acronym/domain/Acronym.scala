package com.acromancer.acromancer.acronym.domain

import java.time.OffsetDateTime
import java.util.UUID
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

import com.acromancer.acromancer.common.Utils
import com.acromancer.acromancer.acronym._

case class Acronym(
  id: AcronymId,
  acronym: String,
  meaning: String
)

object Acronym {

  def withRandomUUID(
    acronym: String,
    meaning: String
  ) = Acronym(UUID.randomUUID(), acronym, meaning)
}

case class BasicAcronymData(id: AcronymId, acronym: String, meaning: String)

object BasicAcronymData {
  def fromAcronym(acronym: Acronym) = new BasicAcronymData(acronym.id, acronym.acronym, acronym.meaning)
}
