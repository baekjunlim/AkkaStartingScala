package org.study.actor

import akka.actor.{Actor, Props, UntypedActorWithStash}
import akka.event.Logging

/**
  * 아카의 상태기계를 구현한 액터
  * @author Baekjun Lim
  */
class PingActor extends UntypedActorWithStash  {
  val log = Logging(context.system, this)
  val child = context.actorOf(Props.create(classOf[Ping1Actor]), "ping1Actor")
  context.become(initial)

  def onReceive(message: Any) = { }

  /** 맨 처음 상태에서 "work" 메시지를 받으면 zeroDone 상태가 된다. */
  def initial : Actor.Receive = {
    case msg@"work" =>
      child ! msg
      context.become(zeroDone)
    case _ => stash()
  }

  /** zeroDone 상태에서 "done" 메시지를 받으면 oneDone 상태가 된다. */
  def zeroDone : Actor.Receive = {
    case msg@"done" =>
      log.info("Received the first done")
      context.become(oneDone)
    case _ => stash()
  }

  /** oneDone 상태에서 "done" 메시지를 받으면 allDone 상태가 된다. */
  def oneDone : Actor.Receive = {
    case msg@"done" =>
      log.info("Received the second done")
      unstashAll()
      context.become(allDone)
    case _ => stash()
  }

  /** allDone 상태에서 "reset" 메시지를 받으면 다시 initial 상태가 된다. */
  def allDone : Actor.Receive = {
    case msg@"reset" =>
      log.info("Received a reset")
      context.become(initial)
    case _ => stash()
  }
}
