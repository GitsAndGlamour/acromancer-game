package com.acromancer.acromancer.passwordreset.application

import com.acromancer.acromancer.common.ConfigWithDefault
import com.typesafe.config.Config

trait PasswordResetConfig extends ConfigWithDefault {
  def rootConfig: Config

  lazy val resetLinkPattern = getString("acromancer.reset-link-pattern", "http://localhost:8080/#/password-reset?code=%s")
}
