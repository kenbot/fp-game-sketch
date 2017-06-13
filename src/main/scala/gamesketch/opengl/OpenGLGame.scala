package gamesketch.opengl

import gamesketch.model.maths.{IntRect, Angle}
import gamesketch.{GameLoop, UserInput, GameScreen}
import gamesketch.framework.GameHarness
import gamesketch.graphics._
import gamesketch.model.{Terrain, Neighbours, GameApplication}
import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw._
import org.lwjgl.system.MemoryUtil._


object OpenGLGame extends OpenGLGameHarness(1024, 768)

class OpenGLGameHarness(width: Int, height: Int) extends GameHarness[GameApplication] {


  // We need to strongly reference callback instances.
  private var errorCallback: GLFWErrorCallback = null
  private var keyCallback: GLFWKeyCallback = null

  // The window handle
  private var window: Long = 0L

  var closeMe = false

  lazy val graphics: Graphics = new OpenGLGraphics(width, height)

  lazy val textureLoader: TextureLoader = new OpenGLTextureLoader

  lazy val gameLoop = new GameLoop(graphics, textureLoader)


  def setup(): Option[GameApplication] = {
    // Setup an error callback. The default implementation
    // will print the error message in System.err.
    errorCallback = GLFWErrorCallback.createPrint(System.err)
    glfwSetErrorCallback(errorCallback)


    // Initialize GLFW. Most GLFW functions will not work before doing this.
    if (glfwInit() != GLFW_TRUE)
      throw new IllegalStateException("Unable to initialize GLFW");

    // Configure our window
    glfwDefaultWindowHints() // optional, the current window hints are already the default
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE) // the window will stay hidden after creation
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE) // the window will be resizable


    // Create the window
    window = glfwCreateWindow(width, height, "Hello World!", NULL, NULL)

    if (window == NULL)
      throw new RuntimeException("Failed to create the GLFW window")

    // Setup a key callback. It will be called every time a key is pressed, repeated or released.
    keyCallback = new GLFWKeyCallback() {
      override def invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int): Unit = {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
          closeMe = true
          //glfwSetWindowShouldClose(window, GLFW_TRUE) // We will detect this in our rendering loop
        }
      }
    }
    glfwSetKeyCallback(window, keyCallback)

    // Get the resolution of the primary monitor
    val vidmode: GLFWVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

    // Center our window
    glfwSetWindowPos(
      window,
      (vidmode.width() - width) / 2,
      (vidmode.height() - height) / 2)

    // Make the OpenGL context current
    glfwMakeContextCurrent(window)

    // Enable v-sync
    glfwSwapInterval(1)

    // Make the window visible
    glfwShowWindow(window)

    Some(GameApplication(width, height, GameScreen.InGame, None, None, quit=false))
  }

  def teardown(game: Option[GameApplication]): Unit = {
    glfwDestroyWindow(window)
    keyCallback.release()

    glfwTerminate()
    errorCallback.release()
  }

  def observeInput(): UserInput = {
    glfwPollEvents()
    UserInput.None
  }

  def render(game: GameApplication, g: Graphics): Unit = {
    gameLoop.render(game)

    glfwSwapBuffers(window) // swap the color buffers
  }

  def runFrame(game: GameApplication, input: UserInput): GameApplication = {
    gameLoop(game, input)
    game
  }

  def shouldQuit(): Boolean = {
    glfwWindowShouldClose(window) == GLFW_TRUE
  }
}
