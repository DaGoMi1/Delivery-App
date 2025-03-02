package Delivery.BE.Handler;

import Delivery.BE.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class) // 사용자를 찾을 수 없을 때
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedRoleException.class) // 사용자의 권한이 올바르지 않을 때
    public ResponseEntity<?> handleUnauthorizedRole(UnauthorizedRoleException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(InvalidOwnerCodeException.class) // 사업자 등록증 코드가 일치하지 않을 때
    public ResponseEntity<?> handleInvalidOwnerCode(InvalidOwnerCodeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InformationNotMatchException.class) // 제공된 정보가 일치하지 않을 때
    public ResponseEntity<?> handleInformationNotMatch(InformationNotMatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(EmailSendException.class) // 이메일 전송 중 오류가 발생했을 때
    public ResponseEntity<?> handleEmailSend(EmailSendException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(AlreadyRegisteredException.class) // 이미 등록된 사용자나 정보가 있을 때
    public ResponseEntity<?> handleAlreadyRegistered(AlreadyRegisteredException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class) // 잘못된 형식이나 유효하지 않은 값이 입력된 경우
    public ResponseEntity<?> handleInvalidInput(InvalidInputException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class) // 예상하지 못한 오류가 발생했을 때 (기타 모든 예외 처리)
    public ResponseEntity<?> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류 발생: " + e.getMessage());
    }
}
