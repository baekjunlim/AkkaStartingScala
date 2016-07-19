package org.study

import akka.actor.{ActorSystem, Props}
import org.study.actor.PingActor

/**
  * 아카의 Let It Crash 철학을 보여주기 위한 메인 클래스
  * @author Baekjun Lim
  */
object BadMain {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("TestSystem")
    val ping = actorSystem.actorOf(Props.create(classOf[PingActor]))
    ping ! "bad"
  }
}
