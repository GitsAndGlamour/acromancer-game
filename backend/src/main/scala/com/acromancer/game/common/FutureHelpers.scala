package com.acromancer.acromancer.common

import scala.concurrent.{ExecutionContext, Future}

object FutureHelpers {
  implicit class PimpedFuture[T](future: Future[T])(implicit val ec: ExecutionContext) {
    def mapToUnit = future.map(_ => ())
  }
}
