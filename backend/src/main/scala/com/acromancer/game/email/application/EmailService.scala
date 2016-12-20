package com.acromancer.acromancer.email.application

import com.acromancer.acromancer.email.domain.EmailContentWithSubject

import scala.concurrent.Future

trait EmailService {

  def scheduleEmail(address: String, emailData: EmailContentWithSubject): Future[Unit]

}
