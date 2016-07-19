package org.study

import akka.actor.{ActorSystem, Props}
import org.study.actor.{BlockingActor, NonblockingActor}

/**
  * 아카의 Future를 이용해서 non-blocking 동작을 보여주는  메인 클래스
  *
  * @author Baekjun Lim
  */
object NonblockingMain {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("TestSystem")
    val nonblockingActor = actorSystem.actorOf(Props[NonblockingActor], "nonblockingActor")
    nonblockingActor ! 10
    nonblockingActor ! "hello"
  }
}
