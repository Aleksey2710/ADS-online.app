package pro.sky.adsonlineapp.dto;

import lombok.Data;
import pro.sky.adsonlineapp.constants.Role;

/**
 * DTO регистрации.
 */
@Data
public class RegisterReq {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
