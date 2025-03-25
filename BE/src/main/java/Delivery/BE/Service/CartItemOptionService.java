package Delivery.BE.Service;

import Delivery.BE.DTO.CreateCartItemOptionDTO;
import Delivery.BE.Domain.CartItem;
import Delivery.BE.Domain.CartItemOption;
import Delivery.BE.Domain.Option;
import Delivery.BE.Repository.CartItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemOptionService {
    private final CartItemOptionRepository cartItemOptionRepository;

    @Transactional
    public void addCartItemOption(CreateCartItemOptionDTO createCartItemOptionDTO) {
        // ID 로 각각의 객체를 받아서
        CartItem cartItem = createCartItemOptionDTO.getCartItem();
        Option option = createCartItemOptionDTO.getOption();

        // 장바구니 메뉴의 옵션으로 추가
        CartItemOption cartItemOption = CartItemOption.builder()
                .cartItem(cartItem)
                .option(option)
                .build();

        cartItemOptionRepository.save(cartItemOption);
    }
}
