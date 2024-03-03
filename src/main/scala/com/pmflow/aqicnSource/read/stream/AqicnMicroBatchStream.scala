package com.pmflow.aqicnSource.read.stream

import org.apache.spark.sql.connector.read.{InputPartition, PartitionReaderFactory}
import org.apache.spark.sql.connector.read.streaming.{MicroBatchStream, Offset}
import org.apache.spark.sql.execution.streaming.LongOffset
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap

import java.util

class AqicnMicroBatchStream (val schema: StructType, val properties: util.Map[String, String], val options: CaseInsensitiveStringMap) extends MicroBatchStream {

    private val interval = options.getInt("interval", 1)
    private val key: String = if (options.containsKey("key")) options.get("key") else null
    private val latlon1: String = if (options.containsKey("latlon1")) options.get("latlon1") else "13.898819,100.415717"
    private val latlon2: String = if (options.containsKey("latlon2")) options.get("latlon2") else "13.579757,100.683936"
  


  override def latestOffset: LongOffset = LongOffset(System.currentTimeMillis() / (1000 * interval))

  override def planInputPartitions(start: Offset, end: Offset): Array[InputPartition] = {
    println("planInputPartitions: " + start + " " + end)
    // bypass with just one partition
    Array(new AqicnInputPartition(1))

  }

  override def createReaderFactory: PartitionReaderFactory = new AqicnPartitionReaderFactory(schema, 
                                                                                              key,
                                                                                              latlon1,
                                                                                              latlon2)

  override def initialOffset: Offset = {
    val initial = System.currentTimeMillis() / (1000 * interval)
    new LongOffset(initial)
  }

  override def deserializeOffset(json: String): Offset = {
    println("deserializeOffset: " + json)
    new LongOffset(json.toLong)
  }

  override def commit(end: Offset): Unit = {

  }

  override def stop(): Unit = {

  }
}
