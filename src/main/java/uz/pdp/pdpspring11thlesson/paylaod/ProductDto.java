package uz.pdp.pdpspring11thlesson.paylaod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;

    private String  name;
    private Integer categoryId;
    private Integer photoId;
    private Integer measurementId;

    public ProductDto(String name, Integer categoryId, Integer photoId, Integer measurementId) {
        this.name = name;
        this.categoryId = categoryId;
        this.photoId = photoId;
        this.measurementId = measurementId;
    }
}
