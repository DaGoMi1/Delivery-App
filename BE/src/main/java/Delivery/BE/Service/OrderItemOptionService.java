package Delivery.BE.Service;

import Delivery.BE.Domain.*;
import Delivery.BE.Repository.OrderItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemOptionService {
    private final OrderItemOptionRepository orderItemOptionRepository;

    public void createOrderItemOptionByList(OrderItem orderItem, List<CartItemOption> cartItemOptions) {
        for (CartItemOption cartItemOption : cartItemOptions) { // 장바구니 메뉴의 옵션을 돌면서
            Option option = cartItemOption.getOption(); // 옵션
            OptionGroup optionGroup = option.getOptionGroup(); // 옵션 그룹

            OrderItemOption orderItemOption = OrderItemOption.builder() // 각 옵션을 주문의 옵션으로 생성
                    .orderItem(orderItem)
                    .optionGroupName(optionGroup.getName())
                    .optionName(option.getName())
                    .optionPrice(option.getPrice())
                    .build();

            orderItemOptionRepository.save(orderItemOption); // 옵션 주문 저장
        }
    }
}
