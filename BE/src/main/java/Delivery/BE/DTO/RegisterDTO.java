package Delivery.BE.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String password2;
    private String phone;
    private String role;
}
