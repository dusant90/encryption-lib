import crypto.CryptoException;
import crypto.CryptoUtils;
import org.apache.commons.io.FileUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionApplication {

    private static final String KEY = "Test Key";
    public static void main(String[] args) throws IOException, CryptoException {

        //INPUT
        File inputFile = new File("src/test/resources/document.txt");
        System.out.println("Input File content to encrypt: " + FileUtils.readFileToString(inputFile));

        //ENCRYPT
        File encryptedFile = File.createTempFile("encrypted", "file");
        CryptoUtils cryptoUtils = new CryptoUtils();

        String encodedKey = Base64.getEncoder().encodeToString(KEY.getBytes());

        cryptoUtils.encrypt(encodedKey, inputFile, encryptedFile);
        System.out.println("Encrypted file content: " + FileUtils.readFileToString(encryptedFile));

        //DECRYPT
        File decryptedFile = File.createTempFile("decrypted", "file");
        cryptoUtils.decrypt(encodedKey, encryptedFile, decryptedFile);
        System.out.println("decrypted File content: " + FileUtils.readFileToString(decryptedFile));
    }
}
