

package ScalaComputations

import scala.io.Source._

object Statistics {
  def numGames(): Int = {
    val list = new java.util.ArrayList[String]()
    val d = new java.io.File("saves/")
    val c = d.listFiles.filter(_.isFile).toList
    c.length
  }

  def numGamesWithBot(): Int = {
    val list = new java.util.ArrayList[String]()
    val d = new java.io.File("saves/")
    d.listFiles.filter(_.isFile).filter(_.getName.toString().startsWith("b")).toList.length
  }
  
//  def scanFiles(filelst: List[String]): java.util.ArrayList[String] = {
//   
//  }

  def longestGame(): String = {
    val d = new java.io.File("saves/")

    val c = d.listFiles.filter(_.isFile).toList

    val lst =  for {
      i <- Range(0, c.length)  
      if (!(c(i).toString().substring(6).startsWith("."))) 
    } yield c(i).toString().substring(6);
    
    val a = new scala.collection.mutable.ArrayBuffer[Int]
    val lenSet = collection.mutable.Set[Int]()
    "%.3f".format(longestComprehension(lst).max.toFloat / 250)
  }
  
  def longestComprehension(l:scala.collection.immutable.IndexedSeq[String]): scala.collection.immutable.IndexedSeq[Int] = {
    for (j <- 0 until l.size) 
      yield fromFile("saves/" + l(j)).getLines.length
     // lenSet += (lines.length)
    
  }
  
    def linesComprehension(l:java.util.ArrayList[String]): scala.collection.immutable.IndexedSeq[String] = {
    for (j <- 0 until l.size) 
      yield fromFile("saves/" + l.get(j)).getLines.toString
     // lenSet += (lines.length)
    
  }
  
  def destroyedComprehension(l:java.util.ArrayList[String]): scala.collection.immutable.IndexedSeq[Int] = {
    for (j <- 0 until l.size) 
      yield fromFile("saves/" + l.get(j)).getLines.length
     // lenSet += (lines.length)
    
  }

  def averageGameDuration(): String = {
    val d = new java.io.File("saves/")

    val c = d.listFiles.filter(_.isFile).toList

    val lst =  for {
      i <- Range(0, c.length) 
      if (!(c(i).toString().substring(6).startsWith("."))) 
    } yield c(i).toString().substring(6);

    val a = new scala.collection.mutable.ArrayBuffer[Int]
    val lenSet = collection.mutable.Set[Int]()
    for (j <- 0 until lst.length) {
      val lines = fromFile("saves/" + lst(j)).getLines
      lenSet += (lines.length)
    }
    "%.3f".format(longestComprehension(lst).sum.toFloat / lenSet.size / 250)
  }
  

  def blocksDestroyed(): Int = {
    val list = new java.util.ArrayList[String]()
    val d = new java.io.File("saves/")

    val c = d.listFiles.filter(_.isFile).toList

    val lst =  for {
      i <- Range(0, c.length) 
      if (!(c(i).toString().substring(6).startsWith("."))) 
    } yield c(i).toString().substring(6);
    
    val a = new scala.collection.mutable.ArrayBuffer[Int]
    val lenSet = collection.mutable.Set[Int]()
    
    
    var slashes = 0
    
    val n2 = for (j <- 0 until lst.length) {
      val lines = fromFile("saves/" + lst(j)).getLines
      
      val n = for {k <- lines}
        yield k.toString().count(_ == '/')-4
      slashes += n.sum
      slashes -= 16
    }
    slashes
  }

  def totalDistanceTravelled(): Int = {
    //val list = new java.util.ArrayList[String]()
    val d = new java.io.File("saves/")

    val c = d.listFiles.filter(_.isFile).toList

     val lst =  for {
      i <- Range(0, c.length) 
      if (!(c(i).toString().substring(6).startsWith("."))) 
    } yield c(i).toString().substring(6);
    
    val a = new scala.collection.mutable.ArrayBuffer[Tuple2[Int, Int]]
    var totalDist = 0.0
    var positions: String = ""
    val ones = scala.collection.mutable.ArrayBuffer[String]()
    for (j <- 0 until lst.length) {
      val lines = fromFile("saves/" + lst(j)).getLines.filter(_.length() < 25)
      //if (j == list.size() - 1) {
        for (k <- lines) {
          positions = k.toString()
          a += Tuple2(positions.split("/")(0).toInt, positions.split("/")(1).toInt)
        }
      //}
      for(i <- 0 until a.length-1){
        totalDist += math.hypot(a(i)._1-a(i+1)._1,a(i)._2-a(i+1)._2)
        //println(totalDist)
      }
      a.clear()
    }
    totalDist.toInt
  }

  def playerWins(): Int = {
    val d = new java.io.File("saves/")

    val c = d.listFiles.filter(_.isFile).toList

     val lst =  for {
      i <- Range(0, c.length) 
      if (!(c(i).toString().substring(6).startsWith("."))) 
    } yield c(i).toString().substring(6);
    
    val a = new scala.collection.mutable.ArrayBuffer[Int]
    val lenSet = collection.mutable.Set[Int]()
    var positions: String = ""
    val ones = scala.collection.mutable.ArrayBuffer[String]()
    for (j <- 0 until lst.length) {
      val lines = fromFile("saves/" + lst(j)).getLines
      for (k <- lines) positions = k.toString()
      
      ones += positions.split("/")(1)
    }
    ones.filter(_ == "1").length
  }


def graphStat(): scala.collection.mutable.ArrayBuffer[Tuple2[Int, Int]] = {
    //val list = new java.util.ArrayList[String]()
    val d = new java.io.File("saves/")

    val c = d.listFiles.filter(_.isFile).toList

     val lst =  for {
      i <- Range(0, c.length) 
      if (!(c(i).toString().substring(6).startsWith("."))) 
    } yield c(i).toString().substring(6);
    
    val a = new scala.collection.mutable.ArrayBuffer[Tuple2[Int, Int]]
    var totalDist = 0.0
    var positions: String = ""
    val ones = scala.collection.mutable.ArrayBuffer[String]()
    for (j <- 0 until lst.length) {
      val lines = fromFile("saves/" + lst(j)).getLines.filter(_.length() < 25)
      //if (j == list.size() - 1) {
        for (k <- lines) {
          positions = k.toString()
          a += Tuple2(positions.split("/")(0).toInt, positions.split("/")(1).toInt)
        }
    }
    a
  }
}