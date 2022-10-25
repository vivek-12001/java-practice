package com.example.crudapplication.spark;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

@Slf4j
public class RepartionVsCollaese {
    public static void main(String[] args) {

        SparkSession spark = SparkSession
                .builder()
                .appName("RepartitionVsCollease")
                .config("spark.master", "local")
                .getOrCreate();

        Dataset<Row> df = spark.read().csv("src/main/resources/input/abc.csv");
        df.show();

        df.coalesce(5).write().mode("overwrite").parquet("src/main/resources/input/parquet/abc");
    }
}
