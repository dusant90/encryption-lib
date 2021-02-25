package com.demo.app.filehandler.api.service;
import encrypt.EncryptionFailedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class FileServiceTest {

    @Autowired FileService fileService = new FileService();


    @Test
    public void testGetFileContent() throws IOException, EncryptionFailedException {
        fileService.getFileContent();
    }
}