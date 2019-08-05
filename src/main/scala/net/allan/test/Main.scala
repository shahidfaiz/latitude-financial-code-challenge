package net.allan.test

import java.nio.file.Paths

import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.io.FileUtils
import scala.collection.JavaConverters._

object Main extends App with LazyLogging {

  val dataDir = "data"
  val config = LoadConfig.load(dataDir, "spec.json")

  FileUtils.writeLines(
    Paths.get(dataDir, "input.txt").toFile,
    config.inputEncoding.name(),
    FixedWidthFileGen.generate(config.columns).asJava
  )
  System.out.println(s"$dataDir/input.txt is generated")

  val fwfLines = FileUtils.readLines(Paths.get(dataDir, "input.txt").toFile, config.inputEncoding).asScala.toList
  val csvLines = CsvParser.parse(config.columns, fwfLines)

  FileUtils.writeLines(
    Paths.get(dataDir, "output.csv").toFile,
    config.outputEncoding.name(),
    CsvParser.parse(config.columns, fwfLines).asJava
  )
  System.out.println(s"$dataDir/output.csv is generated")
}
