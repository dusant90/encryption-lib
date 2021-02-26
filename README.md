# API for uploading/downloadig files with encryption/decryption 

This project contains two separate applications :
   - Encrypion
   - File Handler

# Encrypion-lib

Spring boot Rest API that contains endpoints for file encryption/decryption 

# File Handler

This is Spring Boot Rest API that contains two endpoints for upload/download files to file system

## Running the application

- We need to go to root folder of both applications and build the executable jar with the command below.

```console
mvn clean package -DskipTests
```
- We can find the jars under following path.

```console
{project.root}/target
```
- Running both application should be performed with the command:


```console
java -jar {jar_name}
```
