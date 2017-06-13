package gamesketch.model

import gamesketch.graphics.Image

case class Terrain(name: String, default: Neighbours, images: Map[Neighbours, Image]) {

  def tileWidth: Int = defaultImage.width
  def tileHeight: Int = defaultImage.height

  def defaultImage: Image = images.getOrElse(default, Image.Null)

  def neighbourImage(n: Neighbours): Image =
    images.getOrElse(n, defaultImage)

  override def toString = name
}
