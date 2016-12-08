package drones

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, OutputStream, PrintWriter}

/**
  * Created by Dmytro Bischak on 08.12.16.
  */
class DronesTests extends org.specs2.mutable.Specification {
  "Drones" >> {

    "Two drones integration test" >> {
      val input =
        """5 5
          |1 2 N
          |LMLMLMLMM
          |3 3 E
          |MMRMMRMRRM
          | """.stripMargin

      val expected = Array("1 3 N", "5 1 E")

      val iStream = new ByteArrayInputStream(input.getBytes)

      val outStream = new ByteArrayOutputStream()

      Drones.run(iStream, outStream)

      val result = outStream.toString.split('\n')

      iStream.close()
      outStream.close()

      result must_== expected

    }

    "Rotate left Direction" >> {

      Direction.left(North) mustEqual West
      Direction.left(West) mustEqual South
      Direction.left(South) mustEqual East
      Direction.left(East) mustEqual North

    }

    "Rotate right Direction" >> {

      Direction.right(North) mustEqual East
      Direction.right(East) mustEqual South
      Direction.right(South) mustEqual West
      Direction.right(West) mustEqual North

    }

    "Drone rotate right" >> {
      val platform = Platform(3, 3)
      val drone = Drone(platform, 1, 1, East)

      drone.direction mustEqual East

      drone.rotateRight()
      drone.direction mustEqual South

      drone.rotateRight()
      drone.direction mustEqual West

      drone.rotateRight()
      drone.direction mustEqual North

      drone.rotateRight()
      drone.direction mustEqual East
    }

    "Drone rotates left" >> {
      val platform = Platform(3, 3)
      val drone = Drone(platform, 1, 1, North)

      drone.direction mustEqual North

      drone.rotateLeft()
      drone.direction mustEqual West

      drone.rotateLeft()
      drone.direction mustEqual South

      drone.rotateLeft()
      drone.direction mustEqual East

      drone.rotateLeft()
      drone.direction mustEqual North

    }

    "Drone moves inside platform" >> {

      val platform = Platform(2, 2)
      val drone = Drone(platform, 1, 1, East)

      drone.x mustEqual 1
      drone.y mustEqual 1
      drone.direction mustEqual East

      drone.rotateLeft()
      drone.move()

      drone.x mustEqual 1
      drone.y mustEqual 2
      drone.direction mustEqual North


      drone.rotateLeft()
      drone.move()
      drone.rotateLeft()
      drone.move()

      drone.x mustEqual 0
      drone.y mustEqual 1
      drone.direction mustEqual South

      drone.rotateLeft()
      drone.move()
      drone.move()
      drone.rotateRight()
      drone.move()
      drone.rotateRight()

      drone.x mustEqual 2
      drone.y mustEqual 0
      drone.direction mustEqual West

    }

    "Drone doesn't move outside platform" >> {

      val platform = Platform(2, 2)
      val drone = Drone(platform, 1, 1, East)

      drone.x mustEqual 1
      drone.y mustEqual 1
      drone.direction mustEqual East

      drone.move()
      drone.move()

      drone.x mustEqual 2
      drone.y mustEqual 1
      drone.direction mustEqual East

      drone.rotateRight()
      drone.move()
      drone.move()

      drone.x mustEqual 2
      drone.y mustEqual 0
      drone.direction mustEqual South


      drone.rotateRight()
      drone.move()
      drone.move()
      drone.move()

      drone.x mustEqual 0
      drone.y mustEqual 0
      drone.direction mustEqual West

      drone.rotateRight()
      drone.move()
      drone.move()
      drone.move()

      drone.x mustEqual 0
      drone.y mustEqual 2
      drone.direction mustEqual North

      drone.rotateRight()
      drone.move()
      drone.move()
      drone.move()

      drone.x mustEqual 2
      drone.y mustEqual 2
      drone.direction mustEqual East
    }

  }
}
