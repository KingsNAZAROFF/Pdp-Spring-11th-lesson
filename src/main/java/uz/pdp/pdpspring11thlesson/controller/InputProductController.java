package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.InputProduct;
import uz.pdp.pdpspring11thlesson.paylaod.InputProductDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.InputProductService;

@RestController
@RequestMapping(value = "/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result addInputProductController(@RequestBody InputProductDto dto){
        return inputProductService.addInputProduct(dto);
    }
    @GetMapping
    public Page<InputProduct> getAllController(@RequestParam int page){
        return inputProductService.getAll(page);
    }
    @GetMapping("/{id}")
    public InputProduct getOneController(@PathVariable Integer id){
        return inputProductService.getOne(id);
    }
    @PutMapping("/{id}")
    public Result editInputProductController(@PathVariable Integer id,@RequestBody InputProductDto dto){
        return inputProductService.editInputProduct(id, dto);
    }
    @DeleteMapping("/{id}")
    public Result deleteInputProductController(@PathVariable Integer id){
        return inputProductService.deleteInputProduct(id);
    }
}
