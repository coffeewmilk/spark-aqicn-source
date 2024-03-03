package com.pmflow.aqicnSource.read.stream

import org.apache.spark.sql.connector.read.{InputPartition, PartitionReaderFactory}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap

class AqicnPartitionReaderFactory (val schema: StructType, val key: String, val latlon1: String, val latlon2: String) extends PartitionReaderFactory {

    override def createReader(partition: InputPartition) = new AqicnPartitionReader(partition.asInstanceOf[AqicnInputPartition], 
                                                                                     schema, 
                                                                                     key,
                                                                                     latlon1,
                                                                                     latlon2
                                                                                    )
}
