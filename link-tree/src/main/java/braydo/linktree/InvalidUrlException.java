package braydo.linktree;

public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String message, Throwable context) {
        super(message, context);
    }
}
