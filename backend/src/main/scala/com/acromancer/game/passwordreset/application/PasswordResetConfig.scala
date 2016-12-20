package com.acromancer.game.passwordreset.application

import com.acromancer.game.common.ConfigWithDefault
import com.typesafe.config.Config

trait PasswordResetConfig extends ConfigWithDefault {
  def rootConfig: Config

  lazy val resetLinkPattern = getString("game.reset-link-pattern", "http://localhost:8080/#/password-reset?code=%s")
}
