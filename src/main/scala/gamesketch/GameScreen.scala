package gamesketch

object GameScreen {
  case object InMenu extends GameScreen
  case object InGame extends GameScreen
  case object Credits extends GameScreen
  case object Victory extends GameScreen
}

sealed trait GameScreen
