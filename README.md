# spark-aqicn-source
A simple Spark structured stream source which fetch data from Aqicn website in an interval manner. This is a part of my personal project Pmflow

- The source has no sense of offsets and will only fetch data in an interval manner.
- This source use Map Queries - stations on the map json api. For more details refer to [aqicn-json-api-ref](https://aqicn.org/json-api/doc/#api-Geolocalized_Feed-GetHereFeed).
- This code was develop and tested on Spark 3.5 with Scala 2.12.17
## Usage

add the jar file to spark along with its dependencies or just use assembly jar

```python
input = spark \
            .readStream \
            .format("com.pmflow.aqicnSource.read.stream") \
            .option("interval", 5) \
            .option("key", "[aqinKey]") \
            .load()
```




## To build 

- Assembly jar
  ```
  sbt -J-Xms2048m -J-Xmx2048m assembly
  ```

- Just package
  ```
  sbt package
  ```


