package spark.customSource.read.stream

import org.apache.spark.sql.connector.read.{Scan, ScanBuilder}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap

import java.util

class CustomScanBuilder (val schema: StructType, val properties: util.Map[String, String], val options: CaseInsensitiveStringMap) extends ScanBuilder {
  override def build: Scan = new CustomScan(schema, properties, options)
}
