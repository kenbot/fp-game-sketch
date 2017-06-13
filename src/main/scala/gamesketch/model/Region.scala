package gamesketch.model

import gamesketch.model.maths.IntRect

case class Region(
    tileBounds: IntRect,
    tileGrid: Vector[Vector[Tile]],
    solidToMovement: Boolean = false,
    solidToVision: Boolean = false,
    solidToProjectiles: Boolean = false,
    exploreAll: Boolean = false) {

  def getTileAtPixel(pixelX: Int, pixelY: Int): Option[Tile] = None
  def getTile(tileX: Int, tileY: Int): Option[Tile] = None

  def tilesWide = tileBounds.width
  def tilesHigh = tileBounds.height

  /*
  def tilesWide: Int = {
    val (bw, tw) = (bounds.width.asInstanceOf[Int], terrain.tileWidth)
    bw / tw + (if (bw % tw == 0) 0 else 1)
  }

  def tilesHigh: Int = {
    val (bh, th) = (bounds.height.asInstanceOf[Int], terrain.tileHeight)
    bh / th + (if (bh % th == 0) 0 else 1)
  }
  */
}
