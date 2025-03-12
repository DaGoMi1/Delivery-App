package Delivery.BE.Exception;

public class MissingRequiredDataException extends RuntimeException {
    public MissingRequiredDataException(String message) {
        super(message);
    }
}
