package app;

import crypto.CryptoUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64;

public class EncryptionApplication {

    private static final String KEY = "Test Key";

    public static void main(String[] args) throws Exception {

        //INPUT
//        File inputFile = new File("src/main/resources/document.txt");
//        //File inputFile = getFileFromInput(args);
//        System.out.println("Input File content to encrypt: " + FileUtils.readFileToString(inputFile));
//
//        //ENCRYPT
//        File encryptedFile = File.createTempFile("encrypted", "file");
//        CryptoUtils cryptoUtils = new CryptoUtils();
//
//        String encodedKey = Base64.getEncoder().encodeToString(KEY.getBytes());
//
//        cryptoUtils.encrypt(encodedKey, inputFile, encryptedFile);
//        System.out.println("Encrypted file content: " + FileUtils.readFileToString(encryptedFile));
//
//        //DECRYPT
//        File decryptedFile = File.createTempFile("decrypted", "file");
//        cryptoUtils.decrypt(encodedKey, encryptedFile, decryptedFile);
//        System.out.println("decrypted File content: " + FileUtils.readFileToString(decryptedFile));
    }

    public static File getFileFromInput(String[] args) throws Exception {

        if (args[0] == null || args[0].trim().isEmpty()) {
            throw new FileNotFoundException("You need to specify a path!");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File does not exist");
        }
        return file;
    }
}
