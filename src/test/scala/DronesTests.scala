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

  }
}
