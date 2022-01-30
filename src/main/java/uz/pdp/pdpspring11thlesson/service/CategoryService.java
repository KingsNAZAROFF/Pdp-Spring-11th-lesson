package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Category;
import uz.pdp.pdpspring11thlesson.paylaod.CategoryDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto){
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if(existsByName) {
            return new Result("Bunnday Categoriya mavjud", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent()){
                return new Result("Bunday ota kategoriya mavjud emas",false);
            }
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Muvaffaqiyatli saqlandi",true);

    }

    public List<Category> getAllCategories(){
       return categoryRepository.findAll();
    }

    public Result editCategory(Integer id,CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName) {
            return new Result("Bunnday Categoriya mavjud", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new Result("Bunday kategoriya topilmadi", false);
        }
        Category editingCategory = optionalCategory.get();

        editingCategory.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory1.isPresent()) {
                return new Result("Bunday ota kategoriya mavjud emas", false);
            }
            editingCategory.setParentCategory(optionalCategory1.get());
        }
        categoryRepository.save(editingCategory);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    public Result deleteCategory(Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            categoryRepository.deleteById(id);
            return new Result("Kategoriya o'chirildi",true);
        }
        return new Result("Bunday kategoriya mvjud emas",false);
    }
}
