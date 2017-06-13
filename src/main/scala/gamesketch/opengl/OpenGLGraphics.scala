package gamesketch.opengl

import gamesketch.graphics.{ImageModifiers, Graphics, Image}
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11._

class OpenGLGraphics(windowWidth: Int, windowHeight: Int) extends Graphics {

  GL.createCapabilities()
  glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
  glEnable(GL_TEXTURE_2D)
  glDisable(GL_DEPTH_TEST)
  renderInPixelSpace()
  glEnable(GL_BLEND)
  glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

  override def beginFrame(): Unit = {
    glClear(GL_COLOR_BUFFER_BIT)
  }

  private def renderInPixelSpace(): Unit = {
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    glOrtho(0.0, windowWidth, 0.0, windowHeight, -1.0, 1.0)
    glMatrixMode(GL_MODELVIEW)
    glLoadIdentity()
  }


  override def draw(image: Image, x: Int, y: Int, mods: ImageModifiers): Unit = {

    val im = image.modify(mods)
    val (tint1, tint2, tint3, tint4) = im.tint
    val xx = x + im.mods.offsetX
    val yy = y + im.mods.offsetY

    glBindTexture(GL_TEXTURE_2D, image.texture.id.asInt)

    glBegin(GL_QUADS)

    val (tx1, ty1, tx2, ty2) = (im.textureX1, im.textureY1, im.textureX2, im.textureY2)

    glColor4f(tint1.redf, tint1.greenf, tint1.bluef, tint1.alpha)
    glTexCoord2f(tx1, ty2)
    glVertex2i(xx, yy)

    glColor4f(tint2.redf, tint2.greenf, tint2.bluef, tint2.alpha)
    glTexCoord2f(tx2, ty2)
    glVertex2i(xx + im.width, yy)

    glColor4f(tint3.redf, tint3.greenf, tint3.bluef, tint3.alpha)
    glTexCoord2f(tx2, ty1)
    glVertex2i(xx + im.width, yy + im.height)

    glColor4f(tint4.redf, tint4.greenf, tint4.bluef, tint4.alpha)
    glTexCoord2f(tx1, ty1)
    glVertex2i(xx, yy + im.height)

    glEnd()

    /*             y +1
                     |
             d       |        c
                     |
                     |
   x -1 -------------+----------------- +1
                     |
                     |
             a       |       b
                     |
                     -1

     */
  }

  override def endFrame(): Unit = {
    glFlush()
  }
}
