package com.demo.app.filehandler.api.service;


import crypto.CryptoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class FileServiceTest {

    @Autowired FileService fileService = new FileService();


    @Test
    public void testGetFileContent() throws IOException, CryptoException {
        fileService.getFileContent();
    }
}