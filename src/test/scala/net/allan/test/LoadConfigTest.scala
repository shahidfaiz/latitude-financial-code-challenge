package net.allan.test

import java.nio.charset.{Charset, UnsupportedCharsetException}

import org.scalatest.FunSuite

class LoadConfigTest extends FunSuite {
  val testDataDir = "test-data"

  test("load") {
    val config = LoadConfig.load(testDataDir, "test-spec.json")

    assert(config.inputEncoding == Charset.forName("windows-1252"))
    assert(config.outputEncoding == Charset.forName("utf-8"))
    assert(config.includeHeader)
    assert(config.columns == List(Column("a", 3), Column("b", 4)))
  }

  test("failure") {
    assertThrows[UnsupportedCharsetException] {
      LoadConfig.load(testDataDir, "malformed-spec.json")
    }
  }
}
