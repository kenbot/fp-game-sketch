package gamesketch.model
import goggles._

case class Game(
  currentLevel: GameLevel,
  currentFrame: Int,
  messages: List[GameMessage]) {
}
