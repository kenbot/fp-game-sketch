package gamesketch.gui

import gamesketch.graphics.Graphics

trait Panel {
  def width: Int
  def height: Int

  def draw(g: Graphics): Unit
}
