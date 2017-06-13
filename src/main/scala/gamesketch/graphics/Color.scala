package gamesketch.graphics


object Color {
  def rgba(red: Int, green: Int, blue: Int, alpha: Int): Color = {
    def normalise(c: Int): Int = c % 256
    Color(normalise(red),
          normalise(green),
          normalise(blue),
          normalise(alpha))
  }

  def red = rgba(255, 0, 0, 255)
  def green = rgba(0, 255, 0, 255)
  def blue = rgba(0, 0, 255, 255)
  def white = rgba(255, 255, 255, 255)
  def black = rgba(0, 0, 0, 255)
  def invisible = rgba(0, 0, 0, 0)
}

case class Color private (red: Int, green: Int, blue: Int, alpha: Int) {
  def redf: Float = red / 255.0f
  def bluef: Float = blue / 255.0f
  def greenf: Float = green / 255.0f
  def alphaf: Float = alphaf / 255.0f

  def adjustRed(f: Int => Int): Color =
    Color.rgba(f(red), green, blue, alpha)

  def adjustGreen(f: Int => Int): Color =
    Color.rgba(red, f(green), blue, alpha)

  def adjustBlue(f: Int => Int): Color =
    Color.rgba(red, green, f(blue), alpha)

  def adjustAlpha(f: Int => Int): Color =
    Color.rgba(red, green, blue, f(alpha))

  def &(c: Color): Color = {
    import Math.min
    Color(min(red, c.red), min(green, c.green), min(blue, c.blue), min(alpha, c.alpha))
  }

  def |(c: Color): Color = {
    import Math.max
    Color(max(red, c.red), max(green, c.green), max(blue, c.blue), max(alpha, c.alpha))
  }
}


