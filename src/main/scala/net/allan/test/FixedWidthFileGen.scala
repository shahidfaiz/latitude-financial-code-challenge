package net.allan.test

final case class Column(name: String, width: Int)

/*

Generate lines of fixed width file with dummy content, padded with a single white space between columns for readability

*/
object FixedWidthFileGen {

  def generate(columns: List[Column], numberOfLines: Int = 10): List[String] = genHeader(columns) +: (1 to numberOfLines).toList.map(_ => genLine(columns))

  private def genLine(columns: List[Column]) = columns.map(column => generateContent(column)).mkString
  private def genHeader(columns: List[Column]) = columns.map(column => withPadding(column.name, column)).mkString

  private def generateContent(column: Column) = {
    val content = "A" * (column.width-1)
    withPadding(content, column)
  }

  private def withPadding(content: String, column: Column) = {
    val width = Math.max(column.width, column.name.length+1)
    content + " " * (width - content.length)
  }

}
