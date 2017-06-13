package gamesketch.graphics

import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.image.{AffineTransformOp, BufferedImage}

/*
object SwingGraphics {
  def draw(image: Image, x: Int, y: Int)(implicit g: Graphics2D): Unit =
    new SwingGraphics(g).draw(image, x, y)
}

class SwingGraphics(g: Graphics2D) extends Graphics {

  override def beginFrame(): Unit = ()
  override def endFrame(): Unit = ()


  override def draw(image: Image, x: Int, y: Int): Unit = {

    def rotate(radians: Double, buffer: BufferedImage): BufferedImage = {
      val tx = AffineTransform.getRotateInstance(radians, image.width / 2.0, image.height / 2.0)
      val op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR)
      op.filter(ImageFiles(image.ref), null)
    }

    val (x1, x2) =
      if (!image.flipH) (image.x1, image.x2)
      else (image.x2, image.x1)

    val (y1, y2) =
      if (!image.flipV) (image.y1, image.y2)
      else (image.y2, image.y1)

    val (offX, offY) = (image.offsetX, image.offsetY)


    g.drawImage(rotate(image.rotation.radians, ImageFiles(image.ref)),
        x + offX, y + offY, x + image.width + offX, y + image.height + offY,
        x1, y1, x2, y2,
        null)
  }
}
*/
