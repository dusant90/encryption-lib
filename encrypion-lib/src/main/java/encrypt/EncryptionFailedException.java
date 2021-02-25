package encrypt;

public class EncryptionFailedException extends Exception {

    public EncryptionFailedException() {
    }

    public EncryptionFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
