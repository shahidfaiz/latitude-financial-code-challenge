package net.allan.test

import org.scalatest.FunSuite

class CsvParserTest extends FunSuite {
  val columns = List(Column("a", 2), Column("b", 3), Column("c", 4))

  test("parse") {
    // prepare fixtures
    val lines = List(
      "a b  c   ",
      "A AA AAA ",
      "A AA AAA "
    )

    // SUT
    val csvLines = CsvParser.parse(columns, lines)

    // Verify
    assert(csvLines(0) == "a,b,c")
    assert(csvLines(1) == "A,AA,AAA")
    assert(csvLines(2) == "A,AA,AAA")
  }
}
