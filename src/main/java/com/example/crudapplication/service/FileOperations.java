package com.example.crudapplication.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FileOperations {
    public static void main(String[] args) {

        String directory = "src/main/resources/file";

        /**
         * displays all file under a specific directory only
         */

        try {
            List<File> files = Files.list(Paths.get(directory))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .collect(Collectors.toList());
            files.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * visits all levels of the file tree
         */

        List<Path> fileList = new ArrayList<>();

        try (Stream<Path> fileStream = Files.walk(Paths.get(directory))) {
            fileList = fileStream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .filter(file -> file.getFileName().toString().endsWith(".java"))
                    .collect(Collectors.toList());
            fileList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 1. create file

        String filePath = "src/main/resources/file/";
        try {
            File file = new File(filePath + "newFile.txt");
            String isFileCreated = file.createNewFile() ? "FILE is created" : "FILE already exists";
            System.out.println("isFileCreated = " + isFileCreated);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. read a file
        String newFilePath = filePath + "newFile.txt";
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> fileStream = Files.lines(Paths.get(newFilePath))) {
            fileStream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileContent = contentBuilder.toString();
        System.out.println("fileContent = " + fileContent);

        // 3. read file into bytes
        Path path = Paths.get(newFilePath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            String fileContent1 = new String(bytes);
            System.out.println("fileContent in bytes = " + fileContent1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. read file line by line using java streams
        try (Stream<String> fileStream = Files.lines(path)) {
            List<String> filteredContent = fileStream.filter(x -> x.contains("vivek"))
                    .collect(Collectors.toList());
            filteredContent.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. reading files using File Channel
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(newFilePath, "r");
            FileChannel fileChannel = randomAccessFile.getChannel();) {

            long fileSize = fileChannel.size();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileSize);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            fileChannel.read(byteBuffer);
            byteArrayOutputStream.write(byteBuffer.array());

            String stringBuilder = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);

            System.out.println("byteArrayOutputStream = " + stringBuilder);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 6. write into file using file channel
        String contentToWriteIntoFile = " appending data to file !!!";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(newFilePath, "rw");
             FileChannel fileChannel = randomAccessFile.getChannel();) {

            byte[] strBytes = contentToWriteIntoFile.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(strBytes.length);

            byteBuffer.put(strBytes);
            byteBuffer.flip();
            fileChannel.write(byteBuffer, fileChannel.size());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // file copy
        String srcFile = "src/main/resources/file/newFile.txt";
        String destFile = "src/main/resources/file/subFile/2.txt";

        File fileToCopy = new File(srcFile);
        File newFile = new File(destFile);

        try (FileInputStream fileInputStream = new FileInputStream(fileToCopy);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        ) {
            FileChannel inputChannel = fileInputStream.getChannel();
            FileChannel outputChannel = fileOutputStream.getChannel();

            inputChannel.transferTo(0, fileToCopy.length(), outputChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // search a file
        String fileNameToSearch = "newFile.txt";
        File rootDirectory = new File("D:\\Java Eclipse\\Projects");
        List<File> foundFiles = new ArrayList<>();

        try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
            walkStream.filter(
                    f -> f.toFile().isFile()
            ).forEach(
                    p -> {
                        if (p.toString().endsWith(fileNameToSearch)) {
                            foundFiles.add(p.toFile());
                        }
                    }
            );
            System.out.println("foundFiles = " + foundFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create temp file
        /*File temp;
        try {
            temp = File.createTempFile("testData", ".txt");
            System.out.println("Temp file created : " + temp.getAbsolutePath());
            temp.deleteOnExit();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/

        // create directory
        Path path1 = Paths.get("src/main/resources/file/newDirectory/");
        try {
            if (Files.notExists(path1)) {
                Files.createDirectory(path1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String isEmptyDirectory = Files.list(path1).findAny().isPresent() ? "NOT EMPTY" : "EMPTY";
            System.out.println("isEmptyDirectory = " + isEmptyDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // copy directory
        File srcDir = new File("src/main/resources/file/subFile");
        File destDir = new File("src/main/resources/file/newDirectory");

        try {
            log.info("copying directory from src {} to dest {}", srcDir.getAbsolutePath(), destDir.getAbsolutePath());
            IOFileFilter ioFileFilter = FileFilterUtils.suffixFileFilter(".txt");
            IOFileFilter complexFilter = DirectoryFileFilter.DIRECTORY.or(ioFileFilter);
            FileUtils.copyDirectory(srcDir, destDir, complexFilter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // deletes all files inside a directry
        try {
            log.info("deleting all files inside directory");
            Files.walk(destDir.toPath())
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // file filter
        FileFilter txtFileFilter = (file) -> {
            return file.getName().endsWith(".txt");
        };
        log.info("File Filtering...");
        File[] files = srcDir.listFiles(txtFileFilter);
        Arrays.stream(files).forEach(System.out::println);

    }

}
