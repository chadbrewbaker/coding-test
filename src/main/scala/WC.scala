package coreutils

import scala.io.Source
import scala.util.control.Breaks._
import scala.io.Codec
import java.nio.charset.{CodingErrorAction}

object WC {
  def main(args: Array[String]): Unit = {
    println(Source.DefaultBufSize)
    var total_bytes = 0;
    var total_chars = 0;
    var max_line_length = 0;
    var total_words = 0;
    var total_lines = 0;
    val decoder = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)
    var fnames = scala.collection.mutable.ListBuffer.empty[String]
    for (fname <- args) {
      fnames += fname

      val bufferedSource = Source.fromFile(fname)(decoder)
      //breakable {
        var count = 0
        bufferedSource.foreach { char =>
          count += 1
         // println(s"$char => ${char.toByte}")
         // if (count > 1000) break
        }
        println(count)
     // }

      bufferedSource.close

      for (line <- Source.fromFile(fname)(decoder).getLines) {
        val line_length = line.length
        total_chars += line_length + 1
        if (line_length > max_line_length) {
          max_line_length = line.length
        }
        total_words += line.split("\\s+").length
        total_lines += 1
      }
    }
    println(
      s"  $total_lines $total_words $total_chars $total_bytes $max_line_length $fnames"
    )
  }
}
