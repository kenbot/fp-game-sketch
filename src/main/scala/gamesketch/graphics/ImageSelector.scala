package gamesketch.graphics

import gamesketch.model.maths.IntRect

case class ImageSelector(texture: Texture, val cellWidth: Int, val cellHeight: Int) {

  def apply(cellNum: Int): Image = {
    val rect = forCell(texture.width, texture.height, cellWidth, cellHeight, cellNum)
    Image(texture, rect)
  }

  def images(cellNums: Int*): Seq[Image] =
    cells(
      texture.width,
      texture.height,
      cellWidth,
      cellHeight)(cellNums: _*).map(Image(texture, _))

  private def cells(textureWidth: Int, textureHeight: Int, cellWidth: Int, cellHeight: Int)(cellNums: Int*): Seq[IntRect] =
    cellNums.map(c => forCell(textureWidth, textureHeight, cellWidth, cellHeight, c)).toVector

  private def forCell(bufferWidth: Int, bufferHeight: Int, cellWidth: Int, cellHeight:Int, cell: Int): IntRect = {
    val bw = (bufferWidth / cellWidth) * cellWidth
    val bh = (bufferHeight / cellHeight) * cellHeight
    val row = (cell * cellWidth) / bw

    val x = (cellWidth * cell) % bw
    val y = (cellHeight * row) % bh

    IntRect(x, y, x + cellWidth, y + cellHeight)
  }
}
