package ScalaComputations;

import scala.collection.JavaConverters._
import java.util.ArrayList
import scala.io.Source._
import scala.annotation.tailrec

object BestSort {
  def sort(): ArrayList[String] = {
    val list = new java.util.ArrayList[String]()

    val d = new java.io.File("saves/")

    val c = d.listFiles.filter(_.isFile).toList

    for (i <- Range(0, c.length)) {
      if (!(c(i).toString().substring(6).startsWith("."))) list.add(c(i).toString().substring(6))
    }
    val a = new scala.collection.mutable.ArrayBuffer[Int]
    val map = collection.mutable.Map[Int, String]()
    for (j <- 0 until list.size()) {
      val lines = fromFile("saves/" + list.get(j)).getLines
      map += (lines.length -> list.get(j))
    }
    val t1 = System.nanoTime()
    val vv = msort[Tuple2[Int, String]](_._1 > _._1)(map.toList)
    val t2 = System.nanoTime() - t1
    println("Scala time = " + t2)
    var res = Tuple2(List[Int](), List[String]())
    res = vv.unzip
   // res._2
    val ag = new java.util.ArrayList[String]
    res._2.map(ag.add(_))
    ag
   // res._2.toList.asJava
  }

  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
    @tailrec
    def merge(xs: List[T], ys: List[T], acc: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys.reverse ::: acc
        case (_, Nil) => xs.reverse ::: acc
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) merge(xs1, ys, x :: acc)
          else merge(xs, ys1, y :: acc)
      }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs), Nil).reverse
    }
  }

}