package gamesketch

import gamesketch.model.maths.Facing

sealed trait UserCommand

object UserCommand {
  case class Move(facing: Facing) extends UserCommand
}