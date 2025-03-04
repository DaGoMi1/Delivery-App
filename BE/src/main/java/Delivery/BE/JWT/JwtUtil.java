package Delivery.BE.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${spring.jwt.secretKey}")
    private String SECRET_KEY; // 비밀 키

    @Value("${spring.jwt.access-token-validity}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;// 15분

    @Value("${spring.jwt.refresh-token-validity}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;// 30일

    private SecretKey key;

    @PostConstruct
    public void init() {
        // SECRET_KEY 를 디코딩하여 key 로 초기화
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    // 엑세스 토큰 생성
    public String generateAccessToken(String username) {
        return createToken(username, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    // 리프레시 토큰 생성
    public String generateRefreshToken(String username) {
        return createToken(username, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    // 토큰 생성 메서드
    private String createToken(String subject, long expirationTime) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    // JWT 에서 사용자 ID 추출
    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    // JWT 의 모든 클레임 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    // 토큰 유효성 검사 (토큰 만료 여부만 체크)
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    // 토큰 만료 여부 확인
    private Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

}
