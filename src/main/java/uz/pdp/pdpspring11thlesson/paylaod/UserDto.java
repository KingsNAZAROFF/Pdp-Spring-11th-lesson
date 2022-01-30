package uz.pdp.pdpspring11thlesson.paylaod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String code;

    private String password;
    private Integer warehouseId;
}
