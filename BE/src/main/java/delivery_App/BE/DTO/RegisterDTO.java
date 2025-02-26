package delivery_App.BE.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String password2;
    private String phone;
    private String role;
}
