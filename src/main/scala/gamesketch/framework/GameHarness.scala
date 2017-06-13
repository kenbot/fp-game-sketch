package gamesketch.framework

import gamesketch.UserInput
import gamesketch.graphics.Graphics


trait GameHarness[Game] {

  def main(args: Array[String]): Unit = {
    var game: Option[Game] = None
    try {
      game = setup()
      game.foreach(runGameLoop)
    } finally {
      teardown(game)
    }
  }

  def graphics: Graphics

  def setup(): Option[Game]

  def teardown(game: Option[Game]): Unit

  def observeInput(): UserInput

  def render(game: Game, graphics: Graphics): Unit

  def runFrame(game: Game, input: UserInput): Game

  def shouldQuit(): Boolean

  private def runGameLoop(initGame: Game): Unit = {
    var game = initGame
    while (!shouldQuit()) {
      render(game, graphics)
      game = runFrame(game, observeInput())
    }
  }
}
