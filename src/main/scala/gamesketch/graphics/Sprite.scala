package gamesketch.graphics

import gamesketch.model.maths.{IntRect, Angle}


sealed trait Sprite {
  def currentImage(frame: Int): Image
}

object Image {
  val Null = Image(Texture.Null, IntRect(0,0,0,0))
}


case class Image(texture: Texture,
                 bounds: IntRect,
                 mods: ImageModifiers = ImageModifiers.empty) extends Sprite {
  def x1 = bounds.x1
  def y1 = bounds.y1
  def x2 = bounds.x2
  def y2 = bounds.y2

  def width = bounds.width
  def height = bounds.height

  def flipH = mods.flipH
  def flipV = mods.flipV
  def withFlip(horizontal: Boolean = flipH,
               vertical: Boolean = flipV): Image = {
    copy(mods = mods.copy(flipH = horizontal, flipV = vertical))
  }

  def offsetX = mods.offsetX
  def offsetY = mods.offsetY
  def withOffset(x: Int = offsetX, y: Int = offsetY): Image =
    modified(_.copy(offsetX = x, offsetY = y))

  def rotation = mods.rotation
  def withRotation(rot: Angle): Image =
    modified(_.copy(rotation = rot))

  def tint1 = mods.tint1
  def tint2 = mods.tint2
  def tint3 = mods.tint3
  def tint4 = mods.tint4
  def tint = (tint1, tint2, tint3, tint4)

  def withTint(t1: Color = tint1,
               t2: Color = tint2,
               t3: Color = tint3,
               t4: Color = tint3): Image = {
    modified(_.copy(tint1 = t1, tint2 = t2, tint3 = t3, tint4 = t4))
  }

  private def modified(f: ImageModifiers => ImageModifiers) =
    copy(mods = f(mods))


  def textureX1: Float = texture.proportionalX(x1)
  def textureY1: Float = texture.proportionalY(y1)
  def textureX2: Float = texture.proportionalX(x2)
  def textureY2: Float = texture.proportionalY(y2)


  def modify(newMods: ImageModifiers): Image =
    Image(texture, bounds, mods add newMods)

  final def currentImage(frame: Int): Image = this
}


case class Anim(frame0: Int,
                images: Vector[Image],
                loop: Boolean,
                mods: ImageModifiers = ImageModifiers.empty) extends Sprite {

  def currentImage(frame: Int): Image =
    if (loop) images((frame - frame0) % images.length).modify(mods)
    else images.lift(frame - frame0).fold(Image.Null)(_ modify mods)
}
