package gamesketch

import gamesketch.model._

import java.io.File


object GameFiles {

  def saveGame(game: Game, filename: String): Unit = ???

  def loadGame(filename: String): Option[Game] = ???

  def apply(name: String): File = new File(s"./$name")
}
