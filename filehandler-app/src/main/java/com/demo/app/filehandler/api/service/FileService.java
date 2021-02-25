package com.demo.app.filehandler.api.service;

import encrypt.EncryptionFailedException;
import encrypt.EncryptionProvider;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private static final String KEY = "Test Key";

    public String getFileContent() throws IOException, EncryptionFailedException {

        File file = new File("src/main/resources/static/document.txt");
        System.out.println("Input File content to encrypt: " + FileUtils.readFileToString(file));

        EncryptionProvider encryptionProvider = new EncryptionProvider(KEY);
        File encryptedFile = encryptionProvider.encrypt(file);

        System.out.println("Encrypted file content: " + FileUtils.readFileToString(encryptedFile));

        File decryptedFile = encryptionProvider.decrypt(encryptedFile);
        return FileUtils.readFileToString(decryptedFile);
    }
}
