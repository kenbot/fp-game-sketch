package gamesketch.graphics

import gamesketch.model.maths.Angle

object ImageModifiers {
  val empty = ImageModifiers()
}

case class ImageModifiers(flipH: Boolean = false,
                          flipV: Boolean = false,
                          rotation: Angle = Angle.Zero,
                          offsetX: Int = 0,
                          offsetY: Int = 0,
                          tint1: Color = Color.white,
                          tint2: Color = Color.white,
                          tint3: Color = Color.white,
                          tint4: Color = Color.white) {

  def add(other: ImageModifiers) = {
    def xor(a: Boolean, b: Boolean) =
      a && !b || b && !a

    ImageModifiers(
      xor(flipH, other.flipH),
      xor(flipV, other.flipV),
      rotation + other.rotation,
      offsetX + other.offsetX,
      offsetY + other.offsetY,
      tint1 & other.tint1,
      tint2 & other.tint2,
      tint3 & other.tint3,
      tint4 & other.tint4)
  }
}
