package ScalaComputations

import scala.io.Source._

object Pseudo {
  def generatePseudo(s: String): String = {

    val d = new java.io.File("saves/" + s)

    val blk = new scala.collection.mutable.ArrayBuffer[String]
    val frames = new scala.collection.mutable.ArrayBuffer[Tuple6[String, String, String, String, String, String]]
    var totalDist = 0.0
    var positions: String = ""
    var result: String = "началоИгры\n"
    val lines = fromFile(d).getLines
    for (k <- lines) {
      if (k.length() > 30) {
        positions = k.toString()
        blk ++= positions.split("/")
      } else {
        positions = k.toString()
        positions.count(_ == '/') match {
          case 4 => frames += Tuple6(positions.split("/")(0), positions.split("/")(1), positions.split("/")(2), positions.split("/")(3), "0", "0")
          case 5 => frames += Tuple6(positions.split("/")(0), positions.split("/")(1), positions.split("/")(2), positions.split("/")(3), positions.split("/")(4), "0")
          case 6 => frames += Tuple6(positions.split("/")(0), positions.split("/")(1), positions.split("/")(2), positions.split("/")(3), positions.split("/")(4), positions.split("/")(5))
        }

      }
    }
    for (i <- blk) result += "создатьБлок(" + i + ")\n"
    for (i <- frames) {
      result += "переместитьШар(" + i._1 + ":" + i._2 + ")\nпереместитьВерхнююРакетку(" + i._3 + ":20)\nпереместитьНижнююРакетку(" + i._4 + ":550)\n"
      "0" match {
        case i._5 => result += "удалитьБлок(" + i._5 + ")\n"
        case i._6 => result += "удалитьБлок(" + i._6 + ")\n"
      }

    }
    print(frames)
    result + "конецИгры\n"

  }

}