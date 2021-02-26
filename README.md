# API for uploading/downloadig files with encryption/decryption 

This project contains two separate applications :
   - Encrypion-lib
   - File Handler

# Encrypion-lib

This is java core application that is using AES Symmetric Encryption algorithm for encryption/decryption 

# File Handler

This is Spring Boot Rest API that contains two endpoints for upload/download files to file system
When client uploads the orignal file - file gets automaticly encrypted using configurable master key
Encrypted file is stored on the server.
When client wants to download the file - file gets automatically decrypted using the same master key 

## Running the application

- Run encryption lib to build executable jar

```console
mvn package -DskipTests
```
- Once we have the JAR we need to include that JAR in filehandler application and then run the File-handler app 
