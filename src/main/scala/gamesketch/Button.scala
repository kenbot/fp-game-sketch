package gamesketch

sealed trait Button
case class Key(code: Char) extends Button
case object MouseLeft extends Button
case object MouseMiddle extends Button
case object MouseRight extends Button
