package com.demo.app.filehandler.api.controller;

import com.demo.app.filehandler.api.service.FileService;
import encrypt.EncryptionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/echo")
    public String echo() throws IOException, EncryptionFailedException {

        return fileService.getFileContent();
    }

}
