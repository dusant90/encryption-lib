package crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CryptoUtils {

    private static final String AES_ENCRYPTION = "AES";

    public void encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public void decrypt(String key, File inputFile, File outputFile) throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws CryptoException {
        try {

            Cipher cipher = Cipher.getInstance(AES_ENCRYPTION);
            cipher.init(cipherMode, generateSecretKey(key));

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (Exception e) {
            throw new CryptoException("Error encrypting/decrypting file", e);
        }
    }

    public SecretKey generateSecretKey(String key) throws NoSuchAlgorithmException {

        byte[] decodedKey = key.getBytes();
        SecretKey secretKey = new SecretKeySpec(
                Arrays.copyOf(decodedKey, 16),
                AES_ENCRYPTION
        );
        return secretKey;
    }
}