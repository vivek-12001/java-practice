package com.example.crudapplication.service;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

@Slf4j
public class EncryptFile {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {

        File file = new File("src/main/resources/file/newFile.txt");

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String salt = getRandomSalt();
        String checksum = getFileChecksum(messageDigest, salt, file);

        log.info("checksum created using SHA-256 algo of file {}, added salt is {} is {}", file.getAbsolutePath(), salt, checksum);
    }

    private static String getFileChecksum(MessageDigest messageDigest, String salt,File file) {

        StringBuilder stringBuilder = new StringBuilder();

        messageDigest.update(salt.getBytes());

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

    public static String getRandomSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return bytes.toString();
    }

}
