package delivery_App.BE.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
