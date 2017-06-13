package gamesketch.model

import gamesketch.model.maths.{Vec, Rect}

sealed trait Action

object Action {
  case class Move(eid: EntityId, dir: Vec) extends Action
  case class Accelerate(id: EntityId, amount: Double) extends Action
  case class AffectHealth(amount: Double) extends Action
  case class MakeRegion(rect: Rect) extends Action
}
