package Delivery.BE.Exception;

public class UnauthorizedRoleException extends RuntimeException {
    public UnauthorizedRoleException(String message) {
        super(message);
    }
}
