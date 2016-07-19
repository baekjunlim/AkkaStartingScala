package org.study.service

import akka.actor.Actor
import akka.event.Logging

class PingService extends Actor {
  val log = Logging(context.system, this)

  override def receive = {
    case msg: String =>
      log.info("Received something")
      sender ! "PING"
  }
}
