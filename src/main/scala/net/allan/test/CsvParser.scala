package net.allan.test

/*
Parse fixed-width file lines to csv lines
*/
object CsvParser {
  def parse(columns: List[Column], lines: List[String]) = {
    val columnWidths = columns.map(column => Math.max(column.width, column.name.length+1))
    val offsets = columnWidths.scanLeft(0)(_+_).sliding(2).toList
    lines.map(line => parseLine(offsets, line))
  }

  private def parseLine(offsets: List[List[Int]], line: String) = {
    offsets.map {
      case List(s, e) => line.substring(s, e).trim
    }.mkString(",")
  }
}
