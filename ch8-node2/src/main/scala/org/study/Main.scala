package org.study

import akka.actor.{ActorSystem, Props}
import org.study.service.PingService

/**
  * 아카의 클러스터링을 보여주기 위한 메인 클래스
  * @author Baekjun Lim
  */
object Main {
  /** 백엔드 서비스 역할을 수행하는 PingService 액터를 생성한다. */
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("ClusterSystem")
    val pingService = actorSystem.actorOf(Props.create(classOf[PingService]), "pingService")
  }
}
