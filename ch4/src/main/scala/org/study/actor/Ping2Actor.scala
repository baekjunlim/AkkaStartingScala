package org.study.actor

import akka.actor.Actor
import akka.event.Logging

class Ping2Actor extends Actor {
  val log = Logging(context.system, this)

  override def preRestart(reason: Throwable, message: Option[Any]) = {
    log.info("Ping2Actor preRestart..")
  }

  override def postRestart(reason: Throwable) = {
    log.info("Ping2Actor postRestart..")
  }

  override def postStop = {
    log.info("Ping2Actor postStop..")
  }

  override def receive = {
    case msg@"good" =>
      goodWork
      sender ! "done"
    case msg@"bad" =>
      badWork
  }

  private def goodWork = log.info("Ping2Actor is good.")
  private def badWork = { val a = 1 / 0 } /** 일부러 ArithmeticException을 발생시킨다 */
}
