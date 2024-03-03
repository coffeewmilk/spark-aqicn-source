package spark.customSource.read.stream

import org.apache.spark.sql.connector.read.{InputPartition, PartitionReaderFactory}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap

class CustomPartitionReaderFactory (val schema: StructType, val key: String, val latlon1: String, val latlon2: String) extends PartitionReaderFactory {

    override def createReader(partition: InputPartition) = new CustomPartitionReader(partition.asInstanceOf[CustomInputPartition], 
                                                                                     schema, 
                                                                                     key,
                                                                                     latlon1,
                                                                                     latlon2
                                                                                    )
}
