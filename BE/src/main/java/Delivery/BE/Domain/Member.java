package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false, unique = true) // 아이디
    private String userId;

    @Column(name = "email", nullable = false, unique = true) // 이메일
    private String email;

    @Column(name = "password", nullable = false) // 비밀번호
    private String password;

    @Column(name = "name", nullable = false, unique = true, length = 10) // 닉네임
    private String name;

    @Column(name = "phone", nullable = false, unique = true, length = 20) // 휴대폰 번호
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false) // 역할 정의
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberAddress> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    public enum Role {
        CUSTOMER,   // 손님
        OWNER,      // 점주
        ADMIN      // 관리자
    }
}


