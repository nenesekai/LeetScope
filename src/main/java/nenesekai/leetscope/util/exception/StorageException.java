package nenesekai.leetscope.util.exception;

/**
 * source: https://spring.io/guides/gs/uploading-files/
 */
public class StorageException extends RuntimeException{
    public StorageException(String msg) {
        super(msg);
    }
    public StorageException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
