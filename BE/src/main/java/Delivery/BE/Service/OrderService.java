package Delivery.BE.Service;

import Delivery.BE.DTO.CreateOrderDTO;
import Delivery.BE.DTO.ResponseOrderDTO;
import Delivery.BE.Domain.*;
import Delivery.BE.Exception.MenuUnavailableException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final CartService cartService;
    private final OrderItemService orderItemService;

    @Transactional
    public void createOrder(CreateOrderDTO createOrderDTO) {
        Member member = memberService.getMemberInfo(); // 사용자
        Cart cart = member.getCart(); // 장바구니

        if (cart.getCartItems().isEmpty()) throw new MenuUnavailableException("주문 목록이 비어있습니다.");

        Store store = cart.getCartItems().getFirst().getMenu().getStore(); // 가게
        List<MemberAddress> memberAddresses = member.getAddresses(); // 사용자 주소 리스트
        MemberAddress address = memberAddresses.stream() // 사용자의 메인 주소
                .filter(MemberAddress::isMain).findFirst()
                .orElseThrow(() -> new NotFoundException("회원의 메인 주소가 없습니다."));

        int totalAmount = totalAmountCalculate(cart.getCartItems());

        Order order = Order.builder() // 주문 생성
                .member(member)
                .store(store)
                .address(address.getAddress())
                .detailAddress(address.getDetailAddress())
                .status(Order.Status.PENDING)
                .paymentMethod(createOrderDTO.getPaymentMethod())
                .specialInstructions(createOrderDTO.getSpecialInstructions())
                .totalAmount(totalAmount)
                .build();

        orderRepository.save(order); // 주문 저장

        orderItemService.createOrderItemByList(order, cart.getCartItems()); // 주문의 각 메뉴 생성

        cartService.emptyCart(); // 장바구니 비우기
    }

    public List<ResponseOrderDTO> getOrdersByMember() {
        Member member = memberService.getMemberInfo();
        List<Order> orders = member.getOrders();
        return orders.stream().map(ResponseOrderDTO::new).toList();
    }

    private int totalAmountCalculate(List<CartItem> cartItems) { // 주문 리스트 (장바구니 목록)로 총 가격 계산
        int totalAmount = 0;

        for (CartItem cartItem : cartItems) {
            int menuPrice = cartItem.getMenu().getPrice(); // 주문 메뉴 가격
            int quantity = cartItem.getQuantity(); // 주문 메뉴 수량

            int optionPrice = 0;
            for (CartItemOption cartItemOption : cartItem.getCartItemOptions()) {
                optionPrice += cartItemOption.getOption().getPrice(); // 각 주문 메뉴의 옵션 가격 누적합
            }

            totalAmount += (menuPrice + optionPrice) * quantity; // 최종 가격 += (메뉴 가격 + 메뉴의 옵션 가격) * 수량
        }

        return totalAmount;
    }
}
