package org.study

import akka.actor.{ActorSystem, Props}
import org.study.actor.AgentActor

/**
  * 아카의 에이전트를 보여주기 위한 메인 클래스
  *
  * @author Baekjun Lim
  */
object AgentMain {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("TestSystem")
    val agentActor = actorSystem.actorOf(Props.create(classOf[AgentActor]))
    agentActor ! "start"
  }
}
