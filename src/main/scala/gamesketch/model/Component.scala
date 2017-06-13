package gamesketch.model

import gamesketch.graphics.Image
import gamesketch.model.maths.{Angle, Vec, Rect}

object EntityId {
  val Player = EntityId(-1)
}

case class EntityId(id: Int) extends AnyVal

case class Entities(map: Map[EntityId, Set[Component]]) extends AnyVal

case class Entity(physics: Physics, image: Image, ai: AI, components: Set[Component]) {
  def x = physics.x
  def y = physics.y

  def updatePhysics(f: Physics => Physics): Entity =
    copy(physics = f(physics))
}

object Physics {
  val Zero = Physics(Angle.Zero, Vec.Zero, Vec.Zero, Vec.Zero, 0, 0)

  def sized(width: Int, height: Int): Physics =
    Zero.copy(width = width, height = height)
}

case class Physics(angle: Angle, acc: Vec, vel: Vec, pos: Vec, width: Int, height: Int) {
  def x = pos.x
  def y = pos.y
  def bounds = Rect(x, y, x + width, y + height)

  def move(vec: Vec): Physics =
    copy(pos = pos + vec)

  def accelerateForward(acc: Double): Physics =
    accelerate(Vec.fromAngle(angle, acc))

  def accelerate(acc: Vec): Physics =
    copy(acc = this.acc + acc)

  def rotateTo(a: Angle): Physics =
    copy(angle = a)

  def rotateBy(by: Angle, upTo: Option[Angle] = None): Physics =
    copy(angle = upTo.fold(angle + by)(angle.addUpTo(by, _)))
}


sealed trait Component

case class Health(value: Int) extends Component
case class FacingImage()

