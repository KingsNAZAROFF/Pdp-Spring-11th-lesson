package uz.pdp.pdpspring11thlesson.paylaod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OutputDto {
    private Timestamp date;


    private Integer warehouseId;


    private Integer clientId;


    private Integer currencyId;

    private String factureNumber;
}
