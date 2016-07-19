package org.study

import akka.actor.{ActorSystem, Props}
import org.study.actor.PingActor

object Main {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("TestSystem")
    val ping = actorSystem.actorOf(Props.create(classOf[PingActor]))
    ping ! "work"
    ping ! "reset"
  }
}
