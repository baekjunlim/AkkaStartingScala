package org.study.actor

import akka.actor.Actor
import akka.event.Logging

class CalculationActor extends Actor {
  val log = Logging(context.system, this)

  override def receive = {
    case msg : Int =>
      log.info(s"CalculationActor received $msg")
      work(msg)
      sender ! (msg * 2)
  }

  private def work(n: Int) = {
    log.info(s"CalculationActor working on $n")
    Thread.sleep(1000) // 실전에서는 절대 금물!!!
    log.info(s"CalculationActor completed")
  }
}
