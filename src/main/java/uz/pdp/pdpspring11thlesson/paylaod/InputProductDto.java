package uz.pdp.pdpspring11thlesson.paylaod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {


    private Integer productId;

    private Double amount;

    private Double price;
    private Date expireDate;

    private Integer inputId;
}
