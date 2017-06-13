package gamesketch.opengl

import java.io.InputStream
import java.nio.ByteBuffer

import de.matthiasmann.twl.utils.PNGDecoder
import gamesketch.graphics.{Texture, TextureId, TextureLoader}
import org.lwjgl.opengl.GL11._

import scala.collection.mutable

class OpenGLTextureLoader extends TextureLoader {

  private val textureMap: mutable.Map[String, Texture] = mutable.HashMap()

  def getTexture(name: String): Texture = {
    if (textureMap.contains(name)) {
      textureMap(name)
    } else {
      val tid = loadTexture(name)
      textureMap += name -> tid
      tid
    }
  }

  private def loadTexture(name: String): Texture = {
    val textureId = glGenTextures()
    glBindTexture(GL_TEXTURE_2D, textureId)
    val (textureBuffer, width, height) = loadPngBuffer(name)

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)

    glTexImage2D(
      GL_TEXTURE_2D,
      0,
      GL_RGBA,
      width,
      height,
      0,
      GL_RGBA,
      GL_UNSIGNED_BYTE,
      textureBuffer)

    glBindTexture(GL_TEXTURE_2D, 0)

    Texture(TextureId(textureId), name, width, height)
  }

  private def loadPngBuffer(filename: String): (ByteBuffer, Int, Int) = {
    val in: InputStream = getClass.getClassLoader.getResourceAsStream(filename)
    if (in != null) {
      try {
        val decoder = new PNGDecoder(in)
        val width = decoder.getWidth
        val height = decoder.getHeight

        val buf = ByteBuffer.allocateDirect(4 * width * height)
        decoder.decode(buf, width * 4, PNGDecoder.Format.RGBA)
        buf.flip()
        (buf, width, height)
      } finally {
        in.close()
      }
    } else {
      throw new java.io.IOException(s"Couldn't load image " + filename)
    }
  }
}
