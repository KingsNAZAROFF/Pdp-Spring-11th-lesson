package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Category;
import uz.pdp.pdpspring11thlesson.paylaod.CategoryDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;
    @PostMapping
    public Result addCategoryController(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategoryService(categoryDto);
    }
    @GetMapping
    public List<Category> getAll(){
       return categoryService.getAllCategories();
    }
    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategory(id, categoryDto);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
        return result;
    }

}
