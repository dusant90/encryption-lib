package com.demo.app.filehandler.api.controller;

import com.demo.app.filehandler.api.entity.Response.UploadFileResponse;
import com.demo.app.filehandler.api.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @GetMapping("/echo")
    public String echo() {
        return "Service is up and running";
    }

    @PostMapping("/file/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

        String fileName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/download/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/file/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {

        File file = fileService.loadFileAsResource(fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(getContentType(request, file)))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

    private String getContentType(HttpServletRequest request, File file) {

        String contentType = null;
        contentType = request.getServletContext().getMimeType(file.getAbsolutePath());

        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return contentType;
    }
}
