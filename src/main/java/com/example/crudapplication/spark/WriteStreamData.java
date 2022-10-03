package com.example.crudapplication.spark;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class WriteStreamData {

    public static void main(String[] args) throws StreamingQueryException, TimeoutException {

        SparkSession spark = SparkSession
                .builder()
                .appName("JavaStructuredNetworkWordCount")
                .config("spark.master", "local")
                .getOrCreate();

        /*Dataset<Row> df = spark.read().csv("src/main/resources/input/abc.csv");
        df.show();
        df.write().mode(SaveMode.Append).csv("src/main/resources/input/");
        System.out.println("count : " + df.count());*/



        // Create DataFrame representing the stream of input lines from connection to localhost:9999
        Dataset<Row> lines = spark
                .readStream()
                .format("socket")
                .option("host", "localhost")
                .option("port", 9999)
                .load();

// Split the lines into words
        Dataset<String> words = lines
                .as(Encoders.STRING())
                .flatMap((FlatMapFunction<String, String>) x -> Arrays.asList(x.split(" ")).iterator(), Encoders.STRING());

        /*StreamingQuery query = words.writeStream()
                .outputMode("complete")
                .format("console")
                .start();*/
        StreamingQuery query = words.writeStream()
                .outputMode(OutputMode.Append())
                .format("csv")
                .option("checkpointLocation", "src/main/resources/tmp/")
                .option("path", "src/main/resources/output/")
                .start();

        query.awaitTermination();

    }

}
