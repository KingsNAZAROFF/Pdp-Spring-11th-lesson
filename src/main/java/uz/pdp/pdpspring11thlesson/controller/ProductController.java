package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Product;
import uz.pdp.pdpspring11thlesson.paylaod.ProductDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {


    @Autowired
    ProductService productService;
    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){

        Result result = productService.addProduct(productDto);
        return  result;
    }

    @GetMapping("/all")
    public Page<Product> getAllProducts(@RequestParam int page){
       return productService.getAllProducts(page);
    }
    @GetMapping("/byCategory/{categoryId}")
    public Page<Product> getByCategory(@PathVariable Integer categoryId,@RequestParam int page){
        return productService.getProductsByCategoryId(categoryId,page);
    }
    @GetMapping("{productId}")
    public Result getOneProduct(@PathVariable Integer productId){
        Result result = productService.getProductById(productId);
        return result;
    }
    @PutMapping("/{productId}")
    public Result editProduct(@PathVariable Integer productId,@RequestBody ProductDto productDto){
        Result result = productService.editProduct(productId, productDto);
        return result;
    }
    @DeleteMapping("/{productId}")
    public Result deleteProduct(@PathVariable Integer productId){
        Result result = productService.deleteProduct(productId);
        return result;
    }

}
