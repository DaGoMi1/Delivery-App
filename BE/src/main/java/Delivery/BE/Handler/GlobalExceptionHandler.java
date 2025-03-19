package Delivery.BE.Handler;

import Delivery.BE.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

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
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
    }

    @ExceptionHandler(AlreadyRegisteredException.class) // 이미 등록된 사용자나 정보가 있을 때
    public ResponseEntity<?> handleAlreadyRegistered(AlreadyRegisteredException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(JwtAuthenticationException.class) // Jwt 인증에 실패 했을 때
    public ResponseEntity<String> handleJwtException(JwtAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(MissingRequiredDataException.class) // 필요한 데이터가 누락되었을 때
    public ResponseEntity<?> handleMissingRequiredData(MissingRequiredDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(PasswordReuseException.class) // 비밀번호를 재사용 할 때
    public ResponseEntity<?> handlePasswordReuse(PasswordReuseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // DTO 검증 실패 했을 때
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class) // 객체를 찾을 수 없을 때
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class) // 허용 되지 않은 상태일 때
    public ResponseEntity<?> handleForbiddenException(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class) // 예상하지 못한 오류가 발생했을 때 (기타 모든 예외 처리)
    public ResponseEntity<?> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류 발생: " + e.getMessage());
    }
}
