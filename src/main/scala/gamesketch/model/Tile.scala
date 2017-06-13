package gamesketch.model

import gamesketch.graphics.Image


case class Tile(terrain: Terrain, neighbours: Neighbours) {
  def image: Image = terrain.images(neighbours)
}
