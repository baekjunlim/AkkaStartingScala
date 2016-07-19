package org.study.actor

import akka.actor.Actor
import akka.dispatch.{ExecutionContexts, Mapper}
import akka.event.Logging
import akka.agent.Agent

/**
  * 아카의 에이전트가 동작하는 방식을 보여주는 액터
  *
  * @author Baekjun Lim
  */
class AgentActor extends Actor {
  val log = Logging(context.system, this)

  override def receive = {
    case msg : String =>
      implicit val ec = ExecutionContexts.global()
      val agent = Agent.create(5, ec)
      agent.send(_ * 2)

      // 에이전트의 값이 여전히 5로 출력될 것이다 (아닐 수도)
      log.info(s"Current agent value = ${agent.get}")

      // 일부러 조금 기다린다. 실전에서는 절대 금물!!!
      Thread.sleep(100)

      // 에이전트의 값이 10으로 출력될 것이다 (아닐 수도)
      log.info(s"Current agent value = ${agent.get}")
  }
}
