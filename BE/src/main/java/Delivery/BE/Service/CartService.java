package Delivery.BE.Service;

import Delivery.BE.DTO.ResponseCartDTO;
import Delivery.BE.Domain.Cart;
import Delivery.BE.Domain.CartItem;
import Delivery.BE.Domain.Member;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final MemberService memberService;
    private final CartRepository cartRepository;

    @Transactional
    public void saveCart(Member member) {
        Cart cart = Cart.builder()
                .member(member)
                .build();

        cartRepository.save(cart);
    }

    @Transactional
    public void emptyCart() {
        Member member = memberService.getMemberInfo();
        member.getCart().getCartItems().clear();
    }

    public ResponseCartDTO getCartItems() {
        Member member = memberService.getMemberInfo(); // 사용자 정보를 가져와서
        Long id = member.getCart().getId(); // 사용자의 장바구니 id 가져오기

        Cart cart = findCartById(id);
        List<CartItem> cartItems = cart.getCartItems();

        return new ResponseCartDTO(cartItems);
    }

    public Cart findCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("장바구니를 찾을 수 없습니다. ID: " + id));
    }
}
