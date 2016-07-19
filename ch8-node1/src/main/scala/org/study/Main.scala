package org.study

import akka.actor.{ActorSystem, Props}
import akka.routing.FromConfig
import org.study.http.HttpActor
import org.study.service.PingService

/**
  * 아카의 클러스터링을 보여주기 위한 메인 클래스
  * @author Baekjun Lim
  */
object Main {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("ClusterSystem")
    val router = actorSystem.actorOf(Props.create(classOf[PingService]).withRouter(new FromConfig), "serviceRouter")
    val httpActor = actorSystem.actorOf(Props.create(classOf[HttpActor], router), "httpActor")
  }
}
