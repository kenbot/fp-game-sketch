package gamesketch

import gamesketch.graphics._
import gamesketch.model.maths.{Facing, Vec, Rect, Angle}
import gamesketch.model._

import scala.swing.{MainFrame, Frame}
import scala.swing.Panel
import scala.swing.SimpleSwingApplication
import scala.swing.Swing.ActionListener
import scala.swing.BorderPanel
import scala.swing.event.{Key => SwingKey, KeyPressed}
import java.awt.{Graphics2D, Color, GraphicsDevice, GraphicsEnvironment}
import scala.util.control.NonFatal

import scala.swing.event.{Key => ScalaKey}

/*
object GameSketchSwing { //extends SimpleSwingApplication {
      
  var gameApp = GameApplication(GameScreen.InGame, None, Nil, None)

  Seq("grass.bmp", "human_template.bmp") foreach ImageFiles.load

  val images = ImageRef("grass.bmp").cells(32,32)

  val humanImage = ImageRef("human_template.bmp").cells(32,64)(0).copy(offsetX = -64, offsetY = 16)

  val grass = {
    import Neighbours._
    import Angle.degrees
    Terrain("Grass", images(6), Map(
      U -> images(8),
      D -> images(4),
      L -> images(9),
      R -> images(9).copy(flipH = true),
      UD -> images(2).copy(rotation = degrees(90)),
      LR -> images(2),
      UL -> images(1),
      UR -> images(1).copy(flipH = true),
      DL -> images(3),
      DR -> images(3).copy(flipH = true),
      UDL -> images(0),
      UDR -> images(0).copy(flipH = true),
      ULR -> images(0).copy(rotation = degrees(90)),
      DLR -> images(0).copy(rotation = degrees(270)),
      UDLR -> images(6),
      NONE -> images(5)))
  }

  val level = {
    val region =
      Region(Rect(0,0,1024,768), Color.decode("0x008800"),
        List(Region(Rect(10, 10, 50, 50), Color.green),
             Region(Rect(30, 30, 100, 100), Color.blue)))

    val entities = Map(
        EntityId(0) -> Entity(
          Physics.sized(32,64).copy(pos = Vec(300, 300)),
          humanImage, AI.Empty, Set()))

    GameLevel("Test level", entities, region)

//    import Neighbours._
//      Vector(Tile(grass, UDLR), Tile(grass, ULR),    Tile(grass, UDLR),
//             Tile(grass, UDL),  Tile(grass, NONE),   Tile(grass, UDR),
//             Tile(grass, UDLR), Tile(grass, DLR),    Tile(grass, UDLR)))
  }

  def paintGameLevel(level: GameLevel)(implicit g: Graphics2D): Unit = {
    def paintRegion(r: Region): Unit = {
      import r.bounds._
      g.setColor(r.terrain)
      g.fillRect(
        x1.asInstanceOf[Int],
        y1.asInstanceOf[Int],
        width.asInstanceOf[Int],
        height.asInstanceOf[Int])
    }

    def paintEntity(e: Entity): Unit = {
      SwingGraphics.draw(e.image,
        e.x.asInstanceOf[Int],
        e.y.asInstanceOf[Int])
    }

    level.region foreach paintRegion

    level.entities.values foreach paintEntity
  }

  lazy val top = new MainFrame {
    contents = new BorderPanel {
      val gamePanel = new Panel {
        this.background = Color.red

        override def paintComponent(g: Graphics2D): Unit = {
          //paintGameLevel(level)(g)
        }

      }

      add(gamePanel, BorderPanel.Position.Center)

      listenTo(this.keys)

      import UserCommand.Move
      import Facing._

      this.reactions += {
        case KeyPressed(_, key, _, _) => println(s"Got $key"); addUserCommand(Move(Left))
        case KeyPressed(_, ScalaKey.Left, _, _) => //events ::= Move(Left)
        case KeyPressed(_, ScalaKey.Right, _, _) => // events ::= Move(Right)
        case KeyPressed(_, ScalaKey.Up, _, _) => //events ::= Move(Up)
        case KeyPressed(_, ScalaKey.Down, _, _) => //events ::= Move(Down)
      }
      focusable = true
      requestFocus()
    }
    title = "Game Sketch"
    //centerOnScreen()
    size = java.awt.Toolkit.getDefaultToolkit.getScreenSize
  }

  var userCommands: List[UserCommand] = Nil

  def addUserCommand(c: UserCommand): Unit = {
    this.synchronized {
      userCommands = c :: userCommands
    }
  }

  val gameTimer = new javax.swing.Timer(40, ActionListener {_ =>
    val actions = processCommands(userCommands)
    val changes = actions.foldLeft(ChangeSet.Empty)(performAction)
    val game = gameApp.currentGame.map(changes)
    gameApp = gameApp.copy(currentGame = game)
    top.repaint()
  })

  def performAction(changes: ChangeSet, a: Action): ChangeSet = {
    a match {
      case Action.Move(eid, vec) =>
        changes.forEntity(eid, _.updatePhysics(_ move vec))
    }
  }

  def processCommands(commands: Seq[UserCommand]): Seq[Action] =
    commands.flatMap(userCommandToAction)

  def userCommandToAction(cmd: UserCommand): Seq[Action] =
    cmd match {
      case UserCommand.Move(facing) => Seq(Action.Move(EntityId.Player, facing.vector(10.0)))
      case _ => Nil
    }

  override def startup(args: Array[String]): Unit = {
    super.startup(args)
    gameTimer.start()
  }
  
  override def shutdown(): Unit = {
    gameTimer.stop()
    super.shutdown()
  }
}


trait FullScreen {
  frame: Frame => 
    
  size = java.awt.Toolkit.getDefaultToolkit.getScreenSize
    
  peer.setUndecorated(true)
  val device: GraphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice
  if (device.isFullScreenSupported){
    try device.setFullScreenWindow(peer)
    catch { case NonFatal(_) => device.setFullScreenWindow(null) }
  }
  
  contents.foreach(c => listenTo(c.keys))
  
  reactions += {
    case KeyPressed(_, SwingKey.Escape, _, _) =>
      device.setFullScreenWindow(null)
      frame.peer.setUndecorated(false)
      repaint()
  }

}
*/

