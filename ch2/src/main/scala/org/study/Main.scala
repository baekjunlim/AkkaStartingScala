package org.study

import akka.actor.{ActorSystem, Props}
import org.study.actor.PingActor

object Main {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("TestSystem")
    def ping = actorSystem.actorOf(Props.create(classOf[PingActor]))
    ping ! "pong"
  }
}
