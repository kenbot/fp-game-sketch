package gamesketch.graphics

trait Graphics {
  def beginFrame(): Unit
  def draw(image: Image, x: Int, y: Int, mods: ImageModifiers = ImageModifiers.empty): Unit
  def endFrame(): Unit
}


