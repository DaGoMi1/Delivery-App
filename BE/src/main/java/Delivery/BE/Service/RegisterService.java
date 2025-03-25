package Delivery.BE.Service;

import Delivery.BE.DTO.RegisterDTO;
import Delivery.BE.Domain.Cart;
import Delivery.BE.Domain.Member;
import Delivery.BE.Exception.AlreadyRegisteredException;
import Delivery.BE.Exception.InformationNotMatchException;
import Delivery.BE.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service // 자동으로 Bean 등록
@RequiredArgsConstructor // final이 붙은 필드만 포함하는 생성자 생성
@Transactional // update중 오류가 나면 자동 롤백
public class RegisterService {
    private final MemberRepository memberRepository;
    private final CartService cartService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegisterDTO registerDTO) {

        validateUserId(registerDTO); // userId 유효성 검사
        validateEmail(registerDTO); // email 유효성 검사
        validatePhone(registerDTO); // phone 유효성 검사
        validatePasswordsMatch(registerDTO); // 비밀번호 일치 검사

        Member member = Member.builder()
                .userId(registerDTO.getUserId())
                .password(bCryptPasswordEncoder.encode(registerDTO.getPassword())) // 비밀번호 암호화 후 저장
                .name(registerDTO.getName())
                .email(registerDTO.getEmail())
                .phone(registerDTO.getPhone())
                .role(Member.Role.CUSTOMER) // 초기 회원가입시 고객으로 설정
                .build();

        memberRepository.save(member);

        cartService.saveCart(member);
    }

    private void validateUserId(RegisterDTO registerDTO) {
        if (memberRepository.findByUserId(registerDTO.getUserId()).isPresent()) {// userId가 이미 DB에 저장되어 있다면
            throw new AlreadyRegisteredException("이미 존재하는 아이디입니다.");
        }
    }

    private void validateEmail(RegisterDTO registerDTO) {
        if (memberRepository.findByEmail(registerDTO.getEmail()).isPresent()) {// email이 이미 DB에 저장되어 있다면
            throw new AlreadyRegisteredException("이미 가입한 이메일입니다.");
        }
    }

    private void validatePhone(RegisterDTO registerDTO) {
        if (memberRepository.findByPhone(registerDTO.getPhone()).isPresent()) {// phone이 이미 DB에 저장되어 있다면
            throw new AlreadyRegisteredException("이미 가입한 휴대폰 번호입니다.");
        }
    }

    private void validatePasswordsMatch(RegisterDTO registerDTO) {
        if (!Objects.equals(registerDTO.getPassword(), registerDTO.getPassword2())) { // 두 비밀번호가 일치하지 않는다면
            throw new InformationNotMatchException("비밀번호가 일치하지 않습니다.");
        }
    }
}
