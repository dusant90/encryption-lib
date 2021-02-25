package encrypt;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class EncryptionProviderTest {

    @Test
    public void testEncryptionProvider() throws IOException, EncryptionFailedException {
        //GIVEN
        File file = new File("src/test/resources/document.txt");
        System.out.println("Input File content to encrypt: " + FileUtils.readFileToString(file));

        EncryptionProvider encryptionProvider = new EncryptionProvider("Test Key");
        //WHEN
        File encryptedFile = encryptionProvider.encrypt(file);
        System.out.println("Encrypted file content: " + FileUtils.readFileToString(encryptedFile));

        //THEN
        File decryptedFile = encryptionProvider.decrypt(encryptedFile);
        System.out.println("Decrypted file content: " + FileUtils.readFileToString(decryptedFile));

    }
}