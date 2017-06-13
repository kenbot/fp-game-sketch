package gamesketch.model


object AI {
  val Empty = AI(_ => Nil)
}

case class AI(f: Entity => Seq[Action])
