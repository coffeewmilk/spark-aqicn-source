package spark.customSource.read.stream

import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.connector.read.PartitionReader
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap
import requests.{get => rget, Response}
import ujson.read
import upickle.default
import org.apache.spark.unsafe.types.{UTF8String}

class CustomPartitionReader (val inputPartition: CustomInputPartition, val schema: StructType, val key: String, val latlon1: String, val latlon2: String) extends PartitionReader[InternalRow]
{
    val r: Response = rget("https://api.waqi.info/v2/map/bounds", 
                              params = Map("latlng" -> s"$latlon1,$latlon2",
                                           "token" -> key))
    val data = read(r.text)
    val array = data("data").arr

  override def next: Boolean = {
    !array.isEmpty
  }

  override def get: InternalRow = {
    val row = array.remove(0)
    val values = List(
      UTF8String.fromString(row("aqi").str),
      UTF8String.fromString(row("station")("name").str),
      row("uid").num,
      UTF8String.fromString(row("station")("time").str),
      row("lat").num,
      row("lon").num
    )

    InternalRow.fromSeq(values)
  }

  override def close(): Unit = {

  }
}
