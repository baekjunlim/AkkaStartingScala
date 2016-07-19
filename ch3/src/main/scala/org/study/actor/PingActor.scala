package org.study.actor

import akka.actor.{Actor, Props}
import akka.event.Logging

/**
  * 계층구조 트리의 루트에 해당하는 최상위 부모 액터
  * @author Baekjun Lim
  */
class PingActor extends Actor {
  val log = Logging(context.system, this)
  val child = context.actorOf(Props.create(classOf[Ping1Actor]), "ping1Actor")
  var count = 0

  def receive = {
    case msg@"work" => child ! "work"
    case msg@"done" if count == 0 => count += 1
    case msg@"done" if count == 1 =>
      log.info("all works are completed")
      count = 0
  }
}
