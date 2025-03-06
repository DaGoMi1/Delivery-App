package Delivery.BE.JWT;

import Delivery.BE.Exception.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private static final List<String> PERMIT_ALL_URLS = List.of("/member/register", "/member/find-id"
            ,"/member/find-password", "/auth/**");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        final String jwt = extractJwtFromRequest(request);

        // permitAll이 아닌 URL에서 유효하지 않은 JWT 일때 예외 발생
        if (!isPermitAllUrl(requestUri)) {
            if (jwt == null || !jwtUtil.validateToken(jwt)) {
                throw new JwtAuthenticationException("유효하지 않은 JWT 토큰입니다.");
            }
        }

        if (jwt != null) {
            authenticateUser(jwt);
        }

        filterChain.doFilter(request, response); // 다음 필터로 전달
    }

    // 요청 헤더에서 JWT 추출
    private String extractJwtFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    // JWT 를 검증하고 인증 정보를 설정
    private void authenticateUser(String jwt) {
        String userId = jwtUtil.extractUserId(jwt);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwt)) {
                setAuthentication(userId);
            }
        }
    }

    // SecurityContextHolder 에 인증 정보 설정
    private void setAuthentication(String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean isPermitAllUrl(String requestUri) {
        return PERMIT_ALL_URLS.contains(requestUri);
    }

}
