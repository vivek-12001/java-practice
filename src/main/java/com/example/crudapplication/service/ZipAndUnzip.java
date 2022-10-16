package com.example.crudapplication.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipAndUnzip {

    public static void main(String[] args) throws IOException {

        //singleFileZip();
        multipleFileZip();

    }

    private static void multipleFileZip() throws IOException {

        List<String> filePaths = Arrays.asList("src/main/resources/zipping/a.txt", "src/main/resources/zipping/b.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/zipping/multiFileCompressed.zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        for (String singleFile : filePaths) {
            File file = new File(singleFile);
            FileInputStream fileInputStream = new FileInputStream(file);

            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;

            while ((length = fileInputStream.read(bytes)) > 0) {
                zipOutputStream.write(bytes, 0, length);
            }

            fileInputStream.close();
        }

        zipOutputStream.close();
        fileOutputStream.close();

    }

    private static void singleFileZip() throws IOException {
        String filePath = "src/main/resources/zipping/a.txt";

        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/zipping/compressed.zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        ZipEntry zipEntry = new ZipEntry(file.getName());
        zipOutputStream.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;

        while ((length = fileInputStream.read(bytes)) > 0) {
            zipOutputStream.write(bytes, 0, length);
        }

        zipOutputStream.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
/*

    Emp Dataset
+------+--------+---------------+-----------+-----------+------+------+
        |emp_id|name    |superior_emp_id|year_joined|emp_dept_id|gender|salary|
        +------+--------+---------------+-----------+-----------+------+------+
        |1     |Smith   |-1             |2018       |10         |M     |3000  |
        |2     |Rose    |1              |2010       |20         |M     |4000  |
        |3     |Williams|1              |2010       |10         |M     |1000  |
        |4     |Jones   |2              |2005       |10         |F     |2000  |
        |5     |Brown   |2              |2010       |40         |      |-1    |
        |6     |Brown   |2              |2010       |50         |      |-1    |
        +------+--------+---------------+-----------+-----------+------+------+

        Dept Dataset
        +---------+-------+
        |dept_name|dept_id|
        +---------+-------+
        |Finance  |10     |
        |Marketing|20     |
        |Sales    |30     |
        |IT       |40     |
        +---------+-------+
*/
