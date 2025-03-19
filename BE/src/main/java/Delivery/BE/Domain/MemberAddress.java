package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "member_address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "alias")
    private String alias;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "is_main")
    private boolean isMain;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
