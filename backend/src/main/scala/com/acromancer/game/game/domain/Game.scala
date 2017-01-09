package com.acromancer.acromancer.game.domain

import java.time.OffsetDateTime
import java.util.UUID
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

import com.acromancer.acromancer.common.Utils
import com.acromancer.acromancer.round._
import com.acromancer.acromancer.game._

case class Game(
  id: GameId,
  mode: String,
  firstRound: RoundId,
  secondRound: RoundId,
  thirdRound: RoundId,
  createdOn: OffsetDateTime,
  updatedOn: OffsetDateTime
)

object Game {

  def withRandomUUID(
    mode: String,
    firstRound: RoundId,
    secondRound: RoundId,
    thirdRound: RoundId,
    createdOn: OffsetDateTime,
    updatedOn: OffsetDateTime
  ) = Game(UUID.randomUUID(), mode, firstRound, secondRound, thirdRound, createdOn, updatedOn)
}

case class BasicGameData(id: GameId, mode: String, firstRound: RoundId, secondRound: RoundId, thirdRound: RoundId, createdOn: OffsetDateTime, updatedOn: OffsetDateTime)

object BasicGameData {
  def fromGame(game: Game) = new BasicGameData(game.id, game.mode, game.firstRound, game.secondRound, game.thirdRound, game.createdOn, game.updatedOn)
}
