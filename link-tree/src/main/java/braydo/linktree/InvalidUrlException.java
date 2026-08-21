package braydo.linktree;

/**
 * throw this exception when an invalid url is supplied where work should be done on a complete url
 */
public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String message, Throwable context) {
        super(message, context);
    }
}
