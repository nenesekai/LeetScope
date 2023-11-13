package nenesekai.leetscope.util.exception;

/**
 * source: https://spring.io/guides/gs/uploading-files/
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}