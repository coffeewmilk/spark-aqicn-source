package com.pmflow.aqicnSource.read.stream

import org.apache.spark.sql.connector.read.Scan
import org.apache.spark.sql.connector.read.streaming.MicroBatchStream
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap

import java.util

class AqicnScan (val schema: StructType, val properties: util.Map[String, String], val options: CaseInsensitiveStringMap) extends Scan {
    override def readSchema: StructType = schema

    override def description: String = "custom_scan"

    override def toMicroBatchStream(checkpointLocation: String): MicroBatchStream = new AqicnMicroBatchStream(schema, properties, options)
}
