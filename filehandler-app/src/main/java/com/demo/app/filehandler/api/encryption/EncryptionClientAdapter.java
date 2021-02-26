package com.demo.app.filehandler.api.encryption;

import encrypt.EncryptionFailedException;
import encrypt.EncryptionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class EncryptionClientAdapter {

    @Value("${encryption.secret.key}")
    private String key = "default key";

    private EncryptionProvider encryptionProvider;

    public EncryptionClientAdapter() {
        this.encryptionProvider = new EncryptionProvider(key);
    }

    public File encrypt (MultipartFile file) throws IOException, EncryptionFailedException {

        File tmpInputFile = File.createTempFile("tmp", "input");
        file.transferTo(tmpInputFile);
        return encryptionProvider.encrypt(tmpInputFile);
    }

    public File decrypt (File file) throws EncryptionFailedException {

        return encryptionProvider.decrypt(file);
    }

}
