package gamesketch

import gamesketch.content.TerrainContent
import gamesketch.graphics.{Image, ImageSelector, TextureLoader, Graphics}
import gamesketch.gui.RegionPanel
import gamesketch.model._
import gamesketch.model.maths.{IntRect, Rect}
import goggles._

class GameLoop(graphics: Graphics, textureLoader: TextureLoader) {

  lazy val grassTexture = textureLoader.getTexture("img/terrain/grass.png")
  lazy val humanTexture = textureLoader.getTexture("img/monsters/human_template.png")
  lazy val defaultImage = ImageSelector(grassTexture, 32, 32)(6)

  lazy val grass = Terrain("Grass", Neighbours.UDLR, TerrainContent.groundImageMap(grassTexture))
  lazy val image = Image(humanTexture, IntRect(0, 0, 32, 64))

  private val regionPanel = new RegionPanel(
    Region(Rect(0, 0, 1024, 768), grass),
    200, 200)

  def apply(game: GameApplication, input: UserInput): GameApplication =
    set"$game.currentGame?.currentFrame" += 1


  def render(game: GameApplication): Unit = {
    graphics.beginFrame()

    regionPanel.draw(graphics)

    graphics.draw(image, 32, 32)

    game.currentScreen match {
      case GameScreen.Credits => ()
      case GameScreen.InGame => ()
      case GameScreen.InMenu => ()
      case GameScreen.Victory => ()
    }

    graphics.endFrame()
  }
}
