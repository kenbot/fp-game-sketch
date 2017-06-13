package gamesketch.model


object Neighbours {

  def U = Neighbours(true, false, false, false)
  def D = Neighbours(false, true, false, false)
  def L = Neighbours(false, false, true, false)
  def R = Neighbours(false, false, false, true)

  def UL = Neighbours(true, false, true, false)
  def UR = Neighbours(true, false, false, true)
  def DL = Neighbours(false, true, true, false)
  def DR = Neighbours(false, true, false, true)

  def UD = Neighbours(true, true, false, false)
  def LR = Neighbours(false, false, true, true)

  def UDL = Neighbours(true, true, true, false)
  def UDR = Neighbours(true, true, false, true)
  def ULR = Neighbours(true, false, true, true)
  def DLR = Neighbours(false, true, true, true)

  def UDLR = Neighbours(true, true, true, true)
  def NONE = Neighbours(false, false, false, false)
}

case class Neighbours(up: Boolean, down: Boolean, left: Boolean, right: Boolean) {
  override def toString() = {
    def str(b: Boolean, letter: String) = if (b) letter else "-"
    val u = str(up, "U")
    val d = str(down, "D")
    val l = str(left, "L")
    val r = str(right, "R")
    s"$u$d$l$r"
  }
}
