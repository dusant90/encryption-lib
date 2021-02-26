package com.base.encrypt.controller;

import com.base.encrypt.exception.EncryptionFailedException;
import com.base.encrypt.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("api")
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/echo")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/encryptFile")
    public ResponseEntity<Resource> encryptFile(@RequestParam("file") MultipartFile file) throws IOException, EncryptionFailedException {

        File tmpInputFile = File.createTempFile("tmp", "input");
        file.transferTo(tmpInputFile);
        File encryptedFile = encryptionService.encrypt(tmpInputFile);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(encryptedFile));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

    @PostMapping("/decryptFile")
    public ResponseEntity<Resource> decryptFile(@RequestParam("file") MultipartFile file) throws IOException, EncryptionFailedException {

        File tmpInputFile = File.createTempFile("tmp", "input");
        file.transferTo(tmpInputFile);
        File decryptedFile = encryptionService.decrypt(tmpInputFile);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(decryptedFile));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}
