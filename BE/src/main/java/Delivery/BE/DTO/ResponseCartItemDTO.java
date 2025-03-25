package Delivery.BE.DTO;

import Delivery.BE.Domain.CartItemOption;
import Delivery.BE.Domain.Menu;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseCartItemDTO {
    private int quantity;
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private List<ResponseCartItemOptionDTO> cartItemOptions;

    public ResponseCartItemDTO(int quantity, Menu menu, List<CartItemOption> cartItemOptionList) {
        this.quantity = quantity;
        this.name = menu.getName();
        this.description = menu.getDescription();
        this.price = menu.getPrice();
        this.imageUrl = menu.getImageUrl();
        this.cartItemOptions = cartItemOptionList.stream().map(cartItemOption -> new ResponseCartItemOptionDTO(
                cartItemOption.getOption()
        )).collect(Collectors.toList());
    }
}
