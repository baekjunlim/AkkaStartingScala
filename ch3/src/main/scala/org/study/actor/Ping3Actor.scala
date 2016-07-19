package org.study.actor

import akka.actor.Actor
import akka.event.Logging

/**
  * 계층구조 트리의 최하위 leaf에 해당하는 액터
  * @author Baekjun Lim
  */
class Ping3Actor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case msg@"work" =>
      work
      sender ! "done"
  }

  def work = {
    Thread.sleep(1000) // 실전에서는 금물!!!
    log.info("Ping3 working...")
  }
}
