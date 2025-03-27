package Delivery.BE.Service;

import Delivery.BE.Domain.CartItem;
import Delivery.BE.Domain.Menu;
import Delivery.BE.Domain.Order;
import Delivery.BE.Domain.OrderItem;
import Delivery.BE.Repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemOptionService orderItemOptionService;

    @Transactional
    public void createOrderItemByList(Order order, List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) { // 장바구니의 메뉴를 돌면서
            Menu menu = cartItem.getMenu();

            OrderItem orderItem = OrderItem.builder() // 각 메뉴마다 주문 메뉴로 생성 (장바구니 -> 주문)
                    .order(order)
                    .menuName(menu.getName())
                    .menuDescription(menu.getDescription())
                    .menuPrice(menu.getPrice())
                    .quantity(cartItem.getQuantity())
                    .build();

            orderItemRepository.save(orderItem); // 주문 메뉴 저장

            orderItemOptionService.createOrderItemOptionByList(orderItem, cartItem.getCartItemOptions()); // 주문 옵션 생성
        }
    }
}
