package Delivery.BE.Service;

import Delivery.BE.DTO.EmailFormDTO;
import Delivery.BE.DTO.FindMemberDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Exception.EmailSendException;
import Delivery.BE.Exception.InformationNotMatchException;
import Delivery.BE.Exception.UserNotFoundException;
import Delivery.BE.Repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    public void findUserId(FindMemberDTO findMemberDTO) {
        // 휴대폰 번호로 회원 조회
        Member member = memberRepository.findByPhone(findMemberDTO.getPhone())
                .orElseThrow(() -> new UserNotFoundException("입력한 폰 번호로 등록된 계정이 없습니다."));

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
        Member member = memberRepository.findByUserId(findMemberDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 ID 입니다."));

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
                .orElseThrow(()-> new UserNotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public void withdrawMember(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(()-> new UserNotFoundException("해당 사용자를 찾을 수 없습니다."));

        memberRepository.delete(member);
    }
}