package net.allan.test

import org.scalatest.FunSuite

class FixedWidthFileGenTest extends FunSuite {
  val columns = List(Column("a", 2), Column("b", 3), Column("c", 4))

  test("generate 1 line") {
    val lines = FixedWidthFileGen.generate(columns, 1)

    assert(lines.size == 2)
    assert(lines(0) == "a b  c   ")
    assert(lines(1) == "A AA AAA ")
  }

  test("generate 2 lines") {
    val lines = FixedWidthFileGen.generate(columns, 2)

    assert(lines.size == 3)
    assert(lines(0) == "a b  c   ")
    assert(lines(1) == "A AA AAA ")
    assert(lines(2) == "A AA AAA ")
  }

  test("column name longer than column width") {
    val lines = FixedWidthFileGen.generate(List(Column("aaaa", 2), Column("b", 3), Column("c", 4)), 1)

    assert(lines.size == 2)
    assert(lines(0) == "aaaa b  c   ")
    assert(lines(1) == "A    AA AAA ")
  }

  test("column name same as column width") {
    val lines = FixedWidthFileGen.generate(List(Column("aa", 2), Column("bb", 2), Column("cc", 2)), 1)

    assert(lines.size == 2)
    assert(lines(0) == "aa bb cc ")
    assert(lines(1) == "A  A  A  ")
  }
}
