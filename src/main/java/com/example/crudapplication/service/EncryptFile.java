package com.example.crudapplication.service;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class EncryptFile {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        File file = new File("src/main/resources/file/newFile.txt");

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String checksum = getFileChecksum(messageDigest, file);

        log.info("checksum created using SHA-256 algo of file {} is {}", file.getAbsolutePath(), checksum);
    }

    private static String getFileChecksum(MessageDigest messageDigest, File file) {

        StringBuilder stringBuilder = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] byteArrayToReadDataInChunks = new byte[1024];
            int bytesCount = 0;

            while ((bytesCount = fileInputStream.read(byteArrayToReadDataInChunks)) > 0) {
                messageDigest.update(byteArrayToReadDataInChunks, 0, bytesCount);
            }

            byte[] bytes = messageDigest.digest();

            for(int i=0; i< bytes.length ;i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

}
