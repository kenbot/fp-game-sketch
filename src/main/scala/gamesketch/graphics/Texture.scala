package gamesketch.graphics

object Texture {
  val Null = Texture(TextureId.Null, "", 0, 0)
}

case class Texture(id: TextureId, name: String, width: Int, height: Int) {
  def proportionalX(x: Int): Float =
    x.asInstanceOf[Float] / width.asInstanceOf[Float]

  def proportionalY(y: Int): Float =
    y.asInstanceOf[Float] / height.asInstanceOf[Float]
}
