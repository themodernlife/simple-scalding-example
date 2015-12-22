package net.themodernlife

import com.twitter.scalding._

class WordCount(args: Args) extends Job(args) {
  def tokenize(text: String): Array[String] = {
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")
  }

  val input = args("input")
  val output = args("output")

  TextLine(args("input"))
    .flatMap[String, String]('line → 'word)(tokenize)
    .groupBy('word)(_.size)
    .write(Tsv(output))
}
