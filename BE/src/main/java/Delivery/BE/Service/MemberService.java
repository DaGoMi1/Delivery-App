package Delivery.BE.Service;

import Delivery.BE.DTO.ChangePasswordDTO;
import Delivery.BE.DTO.EmailFormDTO;
import Delivery.BE.DTO.FindMemberDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Exception.*;
import Delivery.BE.Repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    private static final int PASSWORD_LENGTH = 8;

    public Member getMemberInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new JwtAuthenticationException("인증되지 않은 사용자입니다.");
        }

        Object principal = authentication.getPrincipal();
        String userId;

        if (principal instanceof User) {
            userId = ((User) principal).getUsername();
        } else {
            throw new JwtAuthenticationException("유효하지 않은 인증 정보입니다.");
        }

        return findMemberByUserId(userId);
    }

    public void findUserId(FindMemberDTO findMemberDTO) {
        String phone = findMemberDTO.getPhone();
        // 휴대폰 번호로 회원 조회
        Member member = memberRepository.findByPhone(phone)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다. phone: " + phone));

        // Email 형식 만들기
        EmailFormDTO emailFormDTO = new EmailFormDTO();
        emailFormDTO.setEmail(member.getEmail());

        // ID 찾기 서비스에 필요한 Email 형식 만들기
        createIDForm(emailFormDTO, member.getUserId());

        // Email 보내기
        sendEmail(emailFormDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void findPassword(FindMemberDTO findMemberDTO) {
        String userId = findMemberDTO.getUserId();
        Member member = findMemberByUserId(userId);

        if (!member.getPhone().equals(findMemberDTO.getPhone())) {
            throw new InformationNotMatchException("ID와 휴대폰 번호가 일치하지 않습니다.");
        }

        // 임시 비밀번호 생성 후 암호화하여 저장
        String tempPassword = createTempPassword();
        member.setPassword(passwordEncoder.encode(tempPassword));
        memberRepository.save(member);

        // Email 형식 만들기
        EmailFormDTO emailFormDTO = new EmailFormDTO();
        emailFormDTO.setEmail(member.getEmail());

        // Password 찾기 서비스에 필요한 Email 형식 제작
        createPasswordForm(emailFormDTO, tempPassword);

        // Email 보내기
        sendEmail(emailFormDTO);
    }

    private void createIDForm(EmailFormDTO emailFormDTO, String userId) {
        String title = "배달의 부족 - ID 찾기 서비스";
        String content = String.format("""
                안녕하세요. 배달의 부족 ID 찾기 서비스입니다.<br>
                회원님이 가입한 ID는 <strong>%s</strong> 입니다.<br>
                좋은 하루 되세요!
                """, userId);
        emailFormDTO.setTitle(title);
        emailFormDTO.setContent(content);
    }

    private void createPasswordForm(EmailFormDTO emailFormDTO, String tempPassword) {
        String title = "배달의 부족 - Password 찾기 서비스";
        String content = String.format("""
                안녕하세요. 배달의 부족 Password 찾기 서비스입니다.<br>
                회원님의 임시 비밀번호는 <strong>%s</strong> 입니다.<br>
                좋은 하루 되세요!
                """, tempPassword);

        emailFormDTO.setTitle(title);
        emailFormDTO.setContent(content);
    }

    private String createTempPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < PASSWORD_LENGTH; i++) { // 8번
            int index = random.nextInt(CHARACTERS.length()); // 랜덤 인덱스 얻기
            password.append(CHARACTERS.charAt(index)); // 랜덤 인덱스에 있는 문자열을 password 에 추가
        }

        return password.toString();
    }

    private void sendEmail(EmailFormDTO emailFormDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(message, true, "UTF-8");

            mailHelper.setTo(emailFormDTO.getEmail());
            mailHelper.setSubject(emailFormDTO.getTitle());
            mailHelper.setText(emailFormDTO.getContent(), true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailSendException("이메일 전송 서비스가 일시적으로 사용 불가능합니다.", e);
        }
    }

    public Member findMemberByUserId(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(()-> new NotFoundException("사용자를 찾을 수 없습니다. userId: " + userId));
    }

    @Transactional
    public void withdrawMember(String userId) {
        Member member = findMemberByUserId(userId);

        memberRepository.delete(member);
    }

    @Transactional
    public  void changePassword(ChangePasswordDTO changePasswordDTO) {
        String newPassword = changePasswordDTO.getNewPassword();
        String newPassword2 = changePasswordDTO.getNewPassword2();
        String oldPassword = changePasswordDTO.getOldPassword();

        Member member = getMemberInfo();

        if(newPassword.equals(oldPassword)) {
            throw new PasswordReuseException("직전에 사용한 비밀번호를 재사용 할 수 없습니다.");
        }

        if(!newPassword.equals(newPassword2)) {
            throw new InformationNotMatchException("새로운 비밀번호가 일치하지 않습니다.");
        }

        if(!passwordEncoder.matches(oldPassword, member.getPassword())) {
            throw new JwtAuthenticationException("비밀번호 인증에 실패하였습니다.");
        }

        member.setPassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);
    }
}