package com.pmflow.aqicnSource.read.stream

import DefaultSource.getSchema
import org.apache.spark.sql.connector.catalog.Table
import org.apache.spark.sql.connector.catalog.TableProvider
import org.apache.spark.sql.connector.expressions.Transform
import org.apache.spark.sql.types.{DataTypes, Metadata, StructField, StructType}
import org.apache.spark.sql.util.CaseInsensitiveStringMap

import java.util
import javax.xml.crypto.Data

class DefaultSource() extends TableProvider {
  override def inferSchema(options: CaseInsensitiveStringMap): StructType = getSchema

  override def getTable(schema: StructType, partitioning: Array[Transform], properties: util.Map[String, String]): Table = {
    new AqicnTable(schema, properties)
  }

  override def supportsExternalMetadata = true
}


object DefaultSource {
  def getSchema: StructType = {

    val structFields: Array[StructField] = Array[StructField] (
      StructField ("aqi", DataTypes.StringType, true, Metadata.empty),
      StructField ("station", DataTypes.StringType, true, Metadata.empty),
      StructField ("uid", DataTypes.DoubleType, true, Metadata.empty),
      StructField ("time", DataTypes.StringType, true, Metadata.empty),
      StructField ("lat", DataTypes.DoubleType, true, Metadata.empty),
      StructField ("lon", DataTypes.DoubleType, true, Metadata.empty)
    )

    new StructType(structFields)
  }
}


