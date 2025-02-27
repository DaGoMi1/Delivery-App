package delivery_App.BE.Service;

import delivery_App.BE.Domain.Member;
import delivery_App.BE.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

        // UserDetails 객체로 변환 (권한을 설정)
        return new User(
                member.getUserId(), // 사용자 아이디
                member.getPassword(), // 비밀번호
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER")) // 권한 설정
        );
    }
}
