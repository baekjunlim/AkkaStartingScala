package org.study.actor

import akka.actor.{Actor, Props}
import akka.event.Logging

class PingActor extends Actor {
  val log = Logging(context.system, this)
  val pong = context.actorOf(Props.create(classOf[PongActor], self), "pongActor")

  def receive = {
    case msg@"pong" =>
      log.info(s"Ping received $msg")
      pong ! "ping"
  }
}
