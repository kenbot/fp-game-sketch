package gamesketch.model.maths

case class IntRect(x1: Int, y1: Int, x2: Int, y2: Int) {
  def width = x2 - x1
  def height = y2 - y1
}
