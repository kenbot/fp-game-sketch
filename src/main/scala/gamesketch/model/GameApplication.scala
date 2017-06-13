package gamesketch.model

import gamesketch._


case class GameApplication(
  windowWidth: Int,
  windowHeight: Int,
  currentScreen: GameScreen,
  currentGame: Option[Game],
  currentMusic: Option[Sound],
  quit: Boolean)
