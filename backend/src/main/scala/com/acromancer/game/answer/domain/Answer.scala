package com.acromancer.acromancer.answer.domain

import java.time.OffsetDateTime
import java.util.UUID
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

import com.acromancer.acromancer.common.Utils
import com.acromancer.acromancer.answer._
import com.acromancer.acromancer.user._

case class Answer(
  id: AnswerId,
  answer: String,
  userId: UserId,
  votes: Int,
  createdOn: OffsetDateTime,
  updatedOn: OffsetDateTime
)

object Answer {

  def withRandomUUID(
    answer: String,
    userId: UserId,
    votes: Int,
    createdOn: OffsetDateTime,
    updatedOn: OffsetDateTime
  ) = Answer(UUID.randomUUID(), answer, userId, votes, createdOn, updatedOn)
}

case class BasicAnswerData(id: AnswerId, answer: String, userId: UserId, votes: Int, createdOn: OffsetDateTime, updatedOn: OffsetDateTime)

object BasicAnswerData {
  def fromAnswer(answer: Answer) = new BasicAnswerData(answer.id, answer.answer, answer.userId, answer.votes, answer.createdOn, answer.updatedOn)
}
