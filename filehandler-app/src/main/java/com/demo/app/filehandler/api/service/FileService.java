package com.demo.app.filehandler.api.service;

import crypto.CryptoException;
import crypto.CryptoUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private static final String KEY = "Test Key";


    public String getFileContent() throws IOException, CryptoException {

        File file = new File("src/main/resources/static/document.txt");
        System.out.println("Input File content to encrypt: " + FileUtils.readFileToString(file));

        File encryptedFile = File.createTempFile("encrypted", "file");

        CryptoUtils cryptoUtils = new CryptoUtils();
        cryptoUtils.encrypt(KEY, file, encryptedFile);
        System.out.println("Encrypted file content: " + FileUtils.readFileToString(encryptedFile));

        File decryptedFile = File.createTempFile("decrypted", "file");
        cryptoUtils.decrypt(KEY, encryptedFile, decryptedFile);

        return FileUtils.readFileToString(decryptedFile);
    }
}
