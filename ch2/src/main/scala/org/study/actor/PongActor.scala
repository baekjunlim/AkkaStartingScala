package org.study.actor

import akka.actor.{Actor, ActorRef}
import akka.event.Logging

class PongActor(ping: ActorRef) extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case msg@"ping" =>
      log.info(s"Pong received $msg")
      ping ! "pong"
      Thread.sleep(1000) // 실전에서는 절대 금물!!!
  }
}
