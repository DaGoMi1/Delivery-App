package Delivery.BE.Service;

import Delivery.BE.DTO.ResponseAccessTokenDTO;
import Delivery.BE.DTO.ResponseJwtDTO;
import Delivery.BE.DTO.LoginDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Exception.InformationNotMatchException;
import Delivery.BE.Exception.JwtAuthenticationException;
import Delivery.BE.JWT.JwtUtil;
import Delivery.BE.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public ResponseJwtDTO login(LoginDTO loginDTO) {
        Optional<Member> optionalMember = memberRepository.findByUserId(loginDTO.getUserId());

        if (optionalMember.isPresent() && bCryptPasswordEncoder.matches(loginDTO.getPassword(), optionalMember.get().getPassword())) {
            Member member = optionalMember.get(); // Optional 에서 Member 객체 가져오기
            return generateJwtResponse(member.getUserId());
        } else {
            throw new InformationNotMatchException("아이디 또는 비밀번호가 올바르지 않습니다."); // 로그인 실패
        }
    }

    public ResponseAccessTokenDTO reissueAccessToken(String refreshToken) {
        refreshToken = refreshToken.substring(7); // "Bearer " 부분 제거
        String userId = jwtUtil.extractUserId(refreshToken); // Refresh 토큰에서 userId 추출

        if (!jwtUtil.validateToken(refreshToken)) { // 토큰 검증
            throw new JwtAuthenticationException("유효하지 않은 RefreshToken 입니다.");
        }

        String newAccessToken = jwtUtil.generateAccessToken(userId); // Access 토큰 발급

        ResponseAccessTokenDTO responseAccessTokenDTO = new ResponseAccessTokenDTO();
        responseAccessTokenDTO.setAccessToken(newAccessToken); // DTO 에 담기

        return responseAccessTokenDTO;
    }

    private ResponseJwtDTO generateJwtResponse(String userId) {
        String accessToken = jwtUtil.generateAccessToken(userId); // ID로 Access 토큰 발행
        String refreshToken = jwtUtil.generateRefreshToken(userId); // ID로 Refresh 토큰 발행

        ResponseJwtDTO responseJwtDTO = new ResponseJwtDTO();
        responseJwtDTO.setAccessToken(accessToken);
        responseJwtDTO.setRefreshToken(refreshToken);
        return responseJwtDTO;
    }

}

