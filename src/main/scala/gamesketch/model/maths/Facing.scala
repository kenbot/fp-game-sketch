package gamesketch.model.maths


class Facing private (val offset: Int) extends AnyVal {
  def angle: Angle = Angle.degrees(offset * 45)

  def vector(length: Double): Vec =
    Vec.fromAngle(angle, length)
}


object Facing {
  val Up = Facing(0)
  val UpRight = Facing(1)
  val Right = Facing(2)
  val DownRight = Facing(3)
  val Down = Facing(4)
  val DownLeft = Facing(5)
  val Left = Facing(6)
  val UpLeft = Facing(7)

  val values: Seq[Facing] =
    Vector(Up, UpRight, Right, DownRight, Down, DownLeft, Left, UpLeft)

  def fromAngle(a: Angle): Facing = {
    val r = a.radians
    val sixteenth = Math.PI / 8.0

    if (r >= sixteenth * 15 && r < sixteenth) Facing.Up
    else if (r >= sixteenth && r < sixteenth * 3) Facing.UpRight
    else if (r >= sixteenth * 3 && r < sixteenth * 5) Facing.Right
    else if (r >= sixteenth * 5 && r < sixteenth * 7) Facing.DownRight
    else if (r >= sixteenth * 7 && r < sixteenth * 9) Facing.Down
    else if (r >= sixteenth * 9 && r < sixteenth * 11) Facing.DownLeft
    else if (r >= sixteenth * 11 && r < sixteenth * 13) Facing.DownLeft
    else Facing.UpLeft
  }



  def apply(offset: Int): Facing = Facing(offset % 8)

}
