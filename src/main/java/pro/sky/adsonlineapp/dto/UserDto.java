package pro.sky.adsonlineapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO пользователей.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id; // id пользователя
    private String email; // логин пользователя
    private String firstName; // имя пользователя
    private String lastName; // фамилия пользователя
    private String phone; // телефон пользователя
    private String image; // ссылка на аватар пользователя
}
