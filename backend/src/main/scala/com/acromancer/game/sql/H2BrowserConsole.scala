package com.acromancer.acromancer.sql

import com.acromancer.acromancer.common.sql.{DatabaseConfig, SqlDatabase}
import com.typesafe.config.ConfigFactory

object H2BrowserConsole extends App {
  val config = new DatabaseConfig {
    def rootConfig = ConfigFactory.load()
  }

  new Thread(new Runnable {
    def run() = new org.h2.tools.Console().runTool("-url", SqlDatabase.embeddedConnectionStringFromConfig(config))
  }).start()

  println("The console is now running in the background.")
}
