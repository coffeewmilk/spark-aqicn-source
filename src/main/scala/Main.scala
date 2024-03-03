
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQueryException;
import com.pmflow.aqicnSource.read.stream.DefaultSource

object Main {

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
