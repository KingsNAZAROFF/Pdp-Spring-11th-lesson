package uz.pdp.pdpspring11thlesson.paylaod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String message;
    private boolean success;
    private Product product;
    private Object object;


    public Result(String message, boolean success, Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
