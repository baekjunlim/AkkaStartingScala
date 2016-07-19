package org.study.http

import akka.actor.ActorRef
import akka.camel.{CamelMessage, Consumer}
import akka.event.Logging

/**
  * 웹서버 역할을 수행하기 위해서 캐멀과 제티를 사용하는 액터
  * @author Baekjun Lim
  */
class HttpActor(service: ActorRef) extends Consumer {
  val log = Logging(context.system, this)
  def endpointUri = "jetty:http://localhost:8877/akkaStudy"
  var messageSender: ActorRef = ActorRef.noSender

  override def receive = {
    case msg: CamelMessage =>
      log.info("Received something")
      this.messageSender = this.sender
      service ! "Hello"
    case msg: String => this.messageSender ! msg
    case _ => println("Received something unexpected.")
  }
}
