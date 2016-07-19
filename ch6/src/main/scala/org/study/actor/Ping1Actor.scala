package org.study.actor

import akka.actor.{Actor, ActorRef}
import akka.event.Logging

/**
  * 정수를 받으면 자신의 해시코드 값과 함께 화면에 출력하는 간단한 액터
  * @author Baekjun Lim
  */
class Ping1Actor extends Actor {
  val log = Logging(context.system, this)

  override def receive = {
    case msg: Int =>
      log.info(s"Ping1Actor($this.hashcode) received $msg")
      work(msg)
  }

  private def work(n: Integer) {
    log.info(s"Ping1Actor($hashCode()) is working on $n")
    Thread.sleep(1000) // 실전에서는 절대 금물!!!
    log.info(s"Ping1Actor($this.hashCode()) completed")
  }
}
