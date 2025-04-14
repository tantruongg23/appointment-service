package vn.tayjava.exception;

public class IdInvalidException extends RuntimeException {

    public IdInvalidException(String message) {
        super(message);
    }

    public IdInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}