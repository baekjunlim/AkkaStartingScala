package org.study.actor

import akka.actor.{Actor, Props}
import akka.event.Logging


class Ping1Actor extends Actor {
  val log = Logging(context.system, this)
  val child1 = context.actorOf(Props.create(classOf[Ping2Actor]), "ping2Actor")
  val child2 = context.actorOf(Props.create(classOf[Ping3Actor]), "ping3Actor")

  override def receive = {
    case msg : String =>
      log.info(s"Ping1Actor received $msg")
      child1.tell(msg, sender)
      child2.tell(msg, sender)
  }
}
