package org.study.service

import akka.actor.Actor
import akka.agent.Agent
import akka.dispatch.ExecutionContexts
import akka.event.Logging

/**
  * 백엔드 서비스의 역할을 흉내내는 액터.
  * 메시지가 전달되면 단순히 "PING N" 메시지를 리턴한다.
  * N은 PING이 리턴될 때마다 1씩 증가하는 정수.
  *
  * @author Baekjun Lim
  */
class PingService extends Actor {
  val log = Logging(context.system, this)
  implicit val ec = ExecutionContexts.global()
  val count = Agent.create(0, ec)

  override def receive = {
    case msg: String =>
      sender ! s"PING: ${count.get()}"
      count.send(_ + 1)
  }
}
