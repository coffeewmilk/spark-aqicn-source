package spark
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQueryException;
import java.util.concurrent.TimeoutException;
import requests.{get, Response}
import ujson.read
import scala.collection.immutable.Queue
import upickle.default
import spark.customSource.read.stream.DefaultSource

object Main {
    // def main(args: Array[String]): Unit = {
    
    //     val aqicnKey = "c31ef006b061db7684ca6e7e9656356189943c3c"
    //     val latlong1 = Seq("13.898819", "100.415717")
    //     val latlong2 = Seq("13.579757", "100.683936")
    //     val r: Response = get(s"https://api.waqi.info/v2/map/bounds", 
    //                           params = Map("latlng" -> s"${latlong1.mkString(",")},${latlong2.mkString(",")}",
    //                                        "token" -> aqicnKey))
    //     val data = read(r.text)
    //     println(data("data"))
    //     val normalized = data("data").arr

    //     // val unpack: Queue[Map[String, String]] = upickle.default.read[Queue[Map[String, String]]](data("data"))
    // }
    def main(args: Array[String]): Unit = {
        val spark = SparkSession
                    .builder
                    .appName("test")
                    .master("local[*]")
                    .getOrCreate()
        
        val stream = spark.readStream
                    .format(DefaultSource.getClass().getPackageName())
                    .option("interval", 5)
                    .option("key", "c31ef006b061db7684ca6e7e9656356189943c3c")
                    .load()

        val query = stream.writeStream
                    .outputMode("update")
                    .format("console")
                    .start()

        query.awaitTermination()
        
    }
}
