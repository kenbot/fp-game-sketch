package gamesketch

import gamesketch.model.maths.Point

case class UserInput(buttons: Set[Button], mousePosition: Point)

object UserInput {
  val None = UserInput(Set(), Point(0,0))
}
