package com.acromancer.game.email.application

import com.acromancer.game.email.domain.EmailContentWithSubject

import scala.concurrent.Future

trait EmailService {

  def scheduleEmail(address: String, emailData: EmailContentWithSubject): Future[Unit]

}
