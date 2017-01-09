package com.acromancer.acromancer.round.domain

import java.time.OffsetDateTime
import java.util.UUID
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

import com.acromancer.acromancer.common.Utils
import com.acromancer.acromancer.acronym._
import com.acromancer.acromancer.answer._
import com.acromancer.acromancer.round._
import com.acromancer.acromancer.user._

case class Round(
  id: RoundId,
  acronymId: AcronymId,
  firstAnswer: AnswerId,
  secondAnswer: AnswerId,
  thirdAnswer: AnswerId,
  fourthAnswer: AnswerId,
  fifthAnswer: AnswerId,
  sixthAnswer: AnswerId,
  seventhAnswer: AnswerId,
  eighthAnswer: AnswerId,
  createdOn: OffsetDateTime,
  updatedOn: OffsetDateTime
)

object Round {

  def withRandomUUID(
    acronymId: AcronymId,
    firstAnswer: AnswerId,
    secondAnswer: AnswerId,
    thirdAnswer: AnswerId,
    fourthAnswer: AnswerId,
    fifthAnswer: AnswerId,
    sixthAnswer: AnswerId,
    seventhAnswer: AnswerId,
    eighthAnswer: AnswerId,
    createdOn: OffsetDateTime,
    updatedOn: OffsetDateTime
  ) = Round(UUID.randomUUID(), acronymId, firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, fifthAnswer, sixthAnswer, seventhAnswer, eighthAnswer, createdOn, updatedOn)
}

case class BasicRoundData(id: RoundId, acronymId: AcronymId, firstAnswer: AnswerId, secondAnswer: AnswerId, thirdAnswer: AnswerId, fourthAnswer: AnswerId, fifthAnswer: AnswerId, sixthAnswer: AnswerId, seventhAnswer: AnswerId, eighthAnswer: AnswerId, createdOn: OffsetDateTime, updatedOn: OffsetDateTime)

object BasicRoundData {
  def fromRound(round: Round) = new BasicRoundData(round.id, round.firstAnswer, round.acronymId, round.secondAnswer, round.thirdAnswer, round.fourthAnswer, round.fifthAnswer, round.sixthAnswer, round.seventhAnswer, round.eighthAnswer, round.createdOn, round.updatedOn)
}
