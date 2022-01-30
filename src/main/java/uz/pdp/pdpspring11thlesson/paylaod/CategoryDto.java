package uz.pdp.pdpspring11thlesson.paylaod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    private String name;
    private Integer parentCategoryId;

    public CategoryDto(String name, Integer parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }
}
