package spark.customSource.read.stream

import org.apache.spark.sql.connector.catalog.{SupportsRead, TableCapability}
import org.apache.spark.sql.connector.read.ScanBuilder
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap

import java.util


class CustomTable(val schema: StructType, override val properties: util.Map[String, String]) extends SupportsRead {
  private val capabilitiesSet = new util.HashSet[TableCapability]

  override def newScanBuilder(options: CaseInsensitiveStringMap): ScanBuilder = {
    new CustomScanBuilder(schema, properties, options)
  }

  override def name = "custom_table"

  override def capabilities: util.Set[TableCapability] = {
    if (capabilitiesSet.isEmpty) {
      // capabilitiesSet.add(TableCapability.BATCH_READ)
      capabilitiesSet.add(TableCapability.MICRO_BATCH_READ)
    }
    capabilitiesSet
 }
}
