package Delivery.BE.Service;

import Delivery.BE.DTO.CreateCartItemDTO;
import Delivery.BE.DTO.CreateCartItemOptionDTO;
import Delivery.BE.Domain.*;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.MenuUnavailableException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final MenuService menuService;
    private final MemberService memberService;
    private final CartItemOptionService cartItemOptionService;
    private final OptionService optionService;

    @Transactional
    public void addCartItem(CreateCartItemDTO createCartItemDTO) {
        Member member = memberService.getMemberInfo();
        Cart cart = cartService.findCartById(member.getCart().getId());

        Menu menu = menuService.findMenuById(createCartItemDTO.getMenuId());

        if (!menu.isAvailable()) throw new MenuUnavailableException("현재 선택하신 메뉴는 이용 불가능합니다.");

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .menu(menu)
                .quantity(createCartItemDTO.getQuantity())
                .build();

        cartItemRepository.save(cartItem);

        List<Long> list = createCartItemDTO.getOptionIds();

        if (list != null && !list.isEmpty()) { // 옵션 ID가 저장된 리스트가 1개 이상의 데이터를 가지고 있다면
            for (Long optionId : list) {
                Option option = optionService.findOptionById(optionId);

                if (!Objects.equals(option.getOptionGroup().getMenu().getId(), menu.getId()))
                    throw new MenuUnavailableException("해당 메뉴의 옵션이 아닙니다.");

                CreateCartItemOptionDTO createCartItemOptionDTO = CreateCartItemOptionDTO.builder()
                        .cartItem(findCartItemById(cartItem.getId()))
                        .option(optionService.findOptionById(optionId))
                        .build(); // 장바구니 메뉴 ID 와 옵션 ID 를 DTO 로 만들어서

                cartItemOptionService.addCartItemOption(createCartItemOptionDTO); // 장바구니 메뉴의 옵션을 추가
            }
        }
    }

    @Transactional
    public void deleteCartItem(Long id) {
        CartItem cartItem = findCartItemById(id);
        checkCartItemOwner(cartItem);
        cartItemRepository.delete(cartItem);
    }

    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("장바구니 메뉴를 찾을 수 없습니다. ID: " + id));
    }

    public void checkCartItemOwner(CartItem cartItem) { // 요청한 장바구니 메뉴에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();

        if (!Objects.equals(cartItem.getCart().getMember(), member)) {
            throw new ForbiddenException("해당 장바구니의 소유자가 아닙니다.");
        }
    }
}
