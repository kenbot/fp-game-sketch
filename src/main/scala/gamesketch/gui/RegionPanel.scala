package gamesketch.gui

import gamesketch.graphics.Graphics
import gamesketch.model.Region

final class RegionPanel(region: Region,
                        val width: Int,
                        val height: Int) extends Panel {

  private val image = region.terrain.defaultImage

  def draw(g: Graphics): Unit = {
    for (y <- 0 until region.tilesHigh;
         x <- 0 until region.tilesWide) {

      g.draw(image, x * region.tileWidth, y * region.tileHeight)
    }
  }

}
