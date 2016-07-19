package org.study.actor

import akka.actor.{Actor, Props}
import akka.event.Logging

/**
  * 루트(PingActor)의 자식노드에 해당하는 액터.
  * Ping2Actor와 Ping3Actor라는 두 개의 자식노드 액터를 생성.
  *
  * @author Baekjun Lim
  */
class Ping1Actor extends Actor {
  val log = Logging(context.system, this)
  val child1 = context.actorOf(Props.create(classOf[Ping2Actor]), "ping2Actor")
  val child2 = context.actorOf(Props.create(classOf[Ping3Actor]), "ping3Actor")

  def receive = {
    case msg@"work" =>
      log.info(s"Ping1 received $msg")
      child1.tell("work", sender)
      child2.tell("work", sender)
  }
}
