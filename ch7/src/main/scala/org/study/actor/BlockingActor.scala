package org.study.actor

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * 아카의 퓨처를 이용해서 블로킹 동작을 보여주는 액터
  *
  * @author Baekjun Lim
  */
class BlockingActor extends Actor {
  val log = Logging(context.system, this)
  val child = context.actorOf(Props.create(classOf[CalculationActor]), "calculationActor")

  implicit val timeout = Timeout(5 seconds)
  implicit val ec = context.system.dispatcher

  override def receive = {
    case msg: Int =>
      log.info(s"BlockingActor received a message: $msg")
      val future = child ? msg
      Thread.sleep(1000)
      val result = Await.result(future, timeout.duration).asInstanceOf[Int] // 블로킹!!!
      log.info(s"Final result is ${result + 1}")
    case msg: String =>
      log.info(s"BlockingActor received a message: $msg")
  }

}
