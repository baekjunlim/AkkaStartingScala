package org.study.actor

import akka.actor.{Actor, Props}
import akka.event.Logging
import akka.util.Timeout

import scala.concurrent.duration._
import akka.pattern.ask

import scala.util.{Failure, Success}

/**
  * 아카의 퓨처를 이용해서 non-blocking 동작을 보여주는 액터
  *
  * @author Baekjun Lim
  */
class NonblockingActor extends Actor {
  val log = Logging(context.system, this)
  val child = context.actorOf(Props.create(classOf[CalculationActor]), "calculationActor")

  implicit val timeout = Timeout(5 seconds)
  implicit val ec = context.system.dispatcher

  override def receive = {
    case msg : Int =>
      val future = child ? msg
      future onSuccess {
        case result : Int => log.info(s"Succeeded with $result onSuccess")
      }
      future onFailure {
        case ex : Exception => log.info(s"Failed with $ex")
      }
      future onComplete {
        case Success(result) => log.info(s"Succeeded with $result onComplete")
        case Failure(ex) => log.info(s"Failed with $ex")
      }
    case msg: String =>
      log.info(s"NonblockingActor received a message: $msg")
  }
}
