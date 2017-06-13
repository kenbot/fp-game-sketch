package gamesketch.content

import gamesketch.graphics.{Image, ImageSelector, Texture}
import gamesketch.model.Neighbours
import gamesketch.model.maths.Angle

object TerrainContent {

  def groundImageMap(texture: Texture): Map[Neighbours, Image] = {
    val select = ImageSelector(texture, 32, 32)
    import Neighbours._

    Map(
      U -> select(8),
      D -> select(4),
      L -> select(9),
      R -> select(9).withFlip(horizontal = true),
      UD -> select(2).withRotation(Angle.Quarter),
      LR -> select(2),
      UL -> select(1),
      UR -> select(1).withFlip(horizontal = true),
      DL -> select(3),
      DR -> select(3).withFlip(horizontal = true),
      ULR -> select(0).withRotation(Angle.Quarter),
      UDL -> select(0),
      UDR -> select(0).withFlip(horizontal = true),
      DLR -> select(0).withRotation(Angle.ThreeQuarters),
      UDLR -> select.images(6,7,10,11).head,
      NONE -> select(5))
  }

}
