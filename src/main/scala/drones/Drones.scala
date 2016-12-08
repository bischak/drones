package drones

import java.io.{InputStream, OutputStream, PrintStream}
import java.util.Scanner

/**
  * Created by Dmytro Bischak on 08.12.16.
  */


sealed abstract class Direction(name: String, val dx: Int, val dy: Int) {
  override def toString: String = name
}

object North extends Direction("N", 0, 1)

object East extends Direction("E", 1, 0)

object South extends Direction("S", 0, -1)

object West extends Direction("W", -1, 0)

object Direction {

  def apply(name: String): Direction = name match {
    case "N" => North
    case "W" => West
    case "S" => South
    case "E" => East
    case _ => North
  }

  def left(dr: Direction) = dr match {
    case North => West
    case West => South
    case South => East
    case East => North
  }

  def right(dr: Direction) = dr match {
    case North => East
    case East => South
    case South => West
    case West => North
  }
}

case class Platform(width: Int, height: Int)

case class Drone(platform: Platform, var x: Int, var y: Int, var direction: Direction) {

  def move(): Unit = {
    x = math.min(math.max(x + direction.dx, 0), platform.width)
    y = math.min(math.max(y + direction.dy, 0), platform.height)
  }

  def rotateLeft(): Unit = {
    direction = Direction.left(direction)
  }

  def rotateRight(): Unit = {
    direction = Direction.right(direction)
  }

}

object Drones extends App {

  def run(in: InputStream, out: OutputStream): Unit = {

    val scanner = new Scanner(in)
    val outPrint = new PrintStream(out, true)

    val platform = Platform(scanner.nextInt(), scanner.nextInt())

    while (scanner.hasNextInt) {
      val x = scanner.nextInt()
      val y = scanner.nextInt()
      val direction = Direction.apply(scanner.next())

      val drone = Drone(platform, x, y, direction)

      val commands = scanner.next()

      commands.foreach {
        case 'L' => drone.rotateLeft()
        case 'R' => drone.rotateRight()
        case 'M' => drone.move()
      }

      outPrint.println(s"${drone.x} ${drone.y} ${drone.direction.toString}")
    }

    scanner.close()
  }

  run(System.in, System.out)

}
