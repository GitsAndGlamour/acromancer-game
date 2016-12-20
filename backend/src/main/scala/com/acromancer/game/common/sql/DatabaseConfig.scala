package com.acromancer.game.common.sql

import com.acromancer.game.common.ConfigWithDefault
import com.acromancer.game.common.sql.DatabaseConfig._
import com.typesafe.config.Config

trait DatabaseConfig extends ConfigWithDefault {
  def rootConfig: Config

  // format: OFF
  lazy val dbH2Url              = getString(s"game.db.h2.properties.url", "jdbc:h2:file:./data/game")
  lazy val dbPostgresServerName = getString(PostgresServerNameKey, "")
  lazy val dbPostgresPort       = getString(PostgresPortKey, "5432")
  lazy val dbPostgresDbName     = getString(PostgresDbNameKey, "")
  lazy val dbPostgresUsername   = getString(PostgresUsernameKey, "")
  lazy val dbPostgresPassword   = getString(PostgresPasswordKey, "")
}

object DatabaseConfig {
  val PostgresDSClass       = "game.db.postgres.dataSourceClass"
  val PostgresServerNameKey = "game.db.postgres.properties.serverName"
  val PostgresPortKey       = "game.db.postgres.properties.portNumber"
  val PostgresDbNameKey     = "game.db.postgres.properties.databaseName"
  val PostgresUsernameKey   = "game.db.postgres.properties.user"
  val PostgresPasswordKey   = "game.db.postgres.properties.password"
  // format: ON
}
