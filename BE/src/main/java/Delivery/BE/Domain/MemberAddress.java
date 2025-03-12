package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member_address")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
