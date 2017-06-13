package gamesketch.model

sealed trait Event

object Event {
  case class Collision(a: EntityId, b: EntityId) extends Event
  case class Died(a: EntityId) extends Event
}

