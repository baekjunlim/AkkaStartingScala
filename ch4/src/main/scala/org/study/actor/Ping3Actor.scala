package org.study.actor

import akka.actor.Actor
import akka.event.Logging

class Ping3Actor extends Actor {
  val log = Logging(context.system, this)

  override def preRestart(reason: Throwable, message: Option[Any]) = {
    log.info("Ping3Actor preRestart..")
  }

  override def postRestart(reason: Throwable) = {
    log.info("Ping3Actor postRestart..")
  }

  override def postStop = {
    log.info("Ping3Actor postStop..")
  }

  override def receive = {
    case msg@"good" =>
      goodWork
      sender ! "done"
    case msg@"bad" =>
      badWork
  }

  private def goodWork = log.info("Ping3Actor is good.")
  private def badWork = { throw new NullPointerException } /** 일부러 NullPointerException을 발생시킨다 */
}
