package com.acromancer.acromancer.common.sql

import com.acromancer.acromancer.common.ConfigWithDefault
import com.acromancer.acromancer.common.sql.DatabaseConfig._
import com.typesafe.config.Config

trait DatabaseConfig extends ConfigWithDefault {
  def rootConfig: Config

  // format: OFF
  lazy val dbH2Url              = getString(s"acromancer.db.h2.properties.url", "jdbc:h2:file:./data/acromancer")
  lazy val dbPostgresServerName = getString(PostgresServerNameKey, "")
  lazy val dbPostgresPort       = getString(PostgresPortKey, "5432")
  lazy val dbPostgresDbName     = getString(PostgresDbNameKey, "")
  lazy val dbPostgresUsername   = getString(PostgresUsernameKey, "")
  lazy val dbPostgresPassword   = getString(PostgresPasswordKey, "")
}

object DatabaseConfig {
  val PostgresDSClass       = "acromancer.db.postgres.dataSourceClass"
  val PostgresServerNameKey = "acromancer.db.postgres.properties.serverName"
  val PostgresPortKey       = "acromancer.db.postgres.properties.portNumber"
  val PostgresDbNameKey     = "acromancer.db.postgres.properties.databaseName"
  val PostgresUsernameKey   = "acromancer.db.postgres.properties.user"
  val PostgresPasswordKey   = "acromancer.db.postgres.properties.password"
  // format: ON
}
