package Delivery.BE.Exception;

public class InvalidOwnerCodeException extends RuntimeException {
    public InvalidOwnerCodeException(String message) {
        super(message);
    }
}
