package Delivery.BE.DTO;

import Delivery.BE.Domain.CartItem;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseCartDTO {
    private List<ResponseCartItemDTO> cartItems;

    public ResponseCartDTO(List<CartItem> cartItems) {
        this.cartItems = cartItems.stream().map(cartItem -> new ResponseCartItemDTO(
                cartItem.getQuantity(),
                cartItem.getMenu(),
                cartItem.getCartItemOptions()
        )).collect(Collectors.toList());
    }
}
