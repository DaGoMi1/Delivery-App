package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "cart_item_option")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_item_id", nullable = false) // CartItem 와 연결
    private CartItem cartItem;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false) // Option 과 연결
    private Option option;
}
