package encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class EncryptionProvider {

    private static final String AES_ENCRYPTION = "AES";
    private String encodedKey;

    public EncryptionProvider(String key) {
        this.encodedKey = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public File encrypt(File file) throws EncryptionFailedException {
        return execute(Cipher.ENCRYPT_MODE, file);
    }

    public File decrypt(File file) throws EncryptionFailedException {
        return execute(Cipher.DECRYPT_MODE, file);
    }

    private File execute(int cipherMode, File file) throws EncryptionFailedException {
        try {

            Cipher cipher = Cipher.getInstance(AES_ENCRYPTION);
            cipher.init(cipherMode, generateSecretKey(this.encodedKey));

            FileInputStream inputStream = new FileInputStream(file);
            byte[] inputBytes = new byte[(int) file.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            File outputFile = File.createTempFile("output", "file");
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            return outputFile;

        } catch (Exception e) {
            throw new EncryptionFailedException("Error encrypting/decrypting file", e);
        }
    }

    public SecretKey generateSecretKey(String key) throws NoSuchAlgorithmException {

        byte[] decodedKey = key.getBytes();
        return new SecretKeySpec(
                Arrays.copyOf(decodedKey, 16),
                AES_ENCRYPTION
        );
    }
}