package org.study.actor

import akka.actor.{Actor, Props}
import akka.event.Logging
import akka.routing.RoundRobinPool

/**
  * 아카 라우터를 이용해서 자식 액터를 만들고 1부터 10까지의 정수를 보내는 액터
  * @author Baekjun Lim
  */
class PingActor extends Actor {
  val log = Logging(context.system, this)
  val childRouter = context.actorOf(RoundRobinPool(5).props(Props[Ping1Actor]), "ping1Actor")

  def receive = {
    case msg : String =>
      for (i <- 1 to 10) childRouter ! i
      log.info("PingActor sent 10 messages to the router.")
  }
}
