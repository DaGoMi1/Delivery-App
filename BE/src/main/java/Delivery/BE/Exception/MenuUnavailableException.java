package Delivery.BE.Exception;

public class MenuUnavailableException extends RuntimeException {
    public MenuUnavailableException(String message) {
        super(message);
    }
}
