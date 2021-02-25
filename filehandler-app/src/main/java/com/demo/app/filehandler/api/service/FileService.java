package com.demo.app.filehandler.api.service;

import com.demo.app.filehandler.api.entity.FileStorageProperties;
import com.demo.app.filehandler.api.exception.FileStorageException;
import com.demo.app.filehandler.api.exception.MyFileNotFoundException;
import encrypt.EncryptionFailedException;
import encrypt.EncryptionProvider;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    //TODO move the key to configuration
    private static final String KEY = "Test Key";

    private final Path fileStorageLocation;
    private EncryptionProvider encryptionProvider;

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties) {

        this.fileStorageLocation = Paths
                .get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.encryptionProvider = new EncryptionProvider(KEY);

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            //encrypt a file
            // EncryptionProvider encryptionProvider = new EncryptionProvider(KEY);
            File tmpInputFile = File.createTempFile("tmp", "input");
            file.transferTo(tmpInputFile);
            File encryptedFile = encryptionProvider.encrypt(tmpInputFile);

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(FileUtils.openInputStream(encryptedFile),
                    targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException | EncryptionFailedException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File loadFileAsResource(String fileName) throws IOException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return encryptionProvider.decrypt(resource.getFile());
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException | EncryptionFailedException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
