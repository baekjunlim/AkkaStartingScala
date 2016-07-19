package org.study

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import org.study.actor.{AgentActor, BlockingActor}

/**
  * 아카의 Future를 이용해서 blocking 동작을 보여주는  메인 클래스
  *
  * @author Baekjun Lim
  */
object BlockingMain {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("TestSystem")
    val blockingActor = actorSystem.actorOf(Props[BlockingActor], "blockingActor")
    blockingActor ! 10
    blockingActor ! "hello"
  }
}
