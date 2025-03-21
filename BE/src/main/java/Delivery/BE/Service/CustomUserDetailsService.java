package Delivery.BE.Service;

import Delivery.BE.Domain.Member;
import Delivery.BE.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 데이터베이스에서 사용자 정보를 가져옴
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.: " + username));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + member.getRole().name());

        // UserDetails 객체로 변환 (권한을 설정)
        return new User(
                member.getUserId(), // 사용자 아이디
                member.getPassword(), // 비밀번호
                Collections.singletonList(authority) // 권한 설정
        );
    }
}
