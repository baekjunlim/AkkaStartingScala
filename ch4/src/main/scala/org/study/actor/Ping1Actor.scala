package org.study.actor

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy, Props}
import akka.event.Logging
import scala.concurrent.duration._

/**
  * 자식 액터들을 감시하기 위한 전략을 선언하는 액터
  *
  * @author Baekjun Lim
  */
class Ping1Actor extends Actor {
  val log = Logging(context.system, this)
  val child1 = context.actorOf(Props.create(classOf[Ping2Actor]), "ping2Actor")
  val child2 = context.actorOf(Props.create(classOf[Ping3Actor]), "ping3Actor")

  def receive = {
    case msg : String  =>
      log.info(s"Ping1Actor received $msg")
      child1.tell(msg, sender)
      child2.tell(msg, sender)
  }

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: ArithmeticException => Resume  // Ping2Actor는 "bad" 메시지를 받으면 ArithmeticException을 발생
      case _: NullPointerException => Restart // Ping3Actor는 "bad" 메시지를 받으면 NullPointerException을 발생
      case _: IllegalArgumentException => Stop
      case _ => Escalate

    }
}
