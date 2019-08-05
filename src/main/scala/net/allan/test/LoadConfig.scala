package net.allan.test

import java.nio.charset.Charset
import java.nio.file.Paths

final case class Config(
  columns: List[Column],
  inputEncoding: Charset,
  outputEncoding: Charset,
  includeHeader: Boolean
)

object LoadConfig {

  // Not catching exceptions, we want the system to crush when fail parsing config file
  def load(path: String, fileName: String) = {
    val value = ujson.read(Paths.get(path, fileName).toFile)
    val columnNames = value("ColumnNames").str.split(",").map(_.trim)
    val offsets = value("Offsets").str.split(",").map(_.trim)
    val cols = columnNames.zip(offsets).map { case (name, width) => Column(name, width.toInt) }.toList

    val inputEncoding = value("InputEncoding").str
    val incHeader = value("IncludeHeader").bool
    val outputEncoding = value("OutputEncoding").str

    Config(
      columns = cols,
      inputEncoding = Charset.forName(inputEncoding),
      outputEncoding = Charset.forName(outputEncoding),
      includeHeader = incHeader
    )
  }

}
