package org.study.actor

import akka.actor.{Actor, Props}
import akka.event.Logging

/**
  * "good"이나 "bad" 메시지를 받으면 Ping1Actor라는 자식 액터에게 전달.
  * "done" 메시지를 받으면 화면에 결과를 출력.
  * @author Baekjun Lim
  */
class PingActor extends Actor {
  val log = Logging(context.system, this)
  val child = context.actorOf(Props.create(classOf[Ping1Actor]), "ping1Actor")

  def receive = {
    case msg@"good" => child ! msg
    case msg@"bad" => child ! msg
    case msg@"done" => log.info("a task is successfully completed.")
  }
}
