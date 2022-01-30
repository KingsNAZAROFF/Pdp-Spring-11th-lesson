package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.OutputProduct;
import uz.pdp.pdpspring11thlesson.paylaod.OutputProductDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.OutputProductService;

@RestController
@RequestMapping(value = "/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result addOutputProductController(@RequestBody OutputProductDto dto){
        return outputProductService.addOutputProduct(dto);
    }
    @GetMapping
    public Page<OutputProduct> getAllController(@RequestParam int page){
        return outputProductService.getAll(page);
    }
    @GetMapping("/{id}")
    public OutputProduct getOneController(@PathVariable Integer id){
        return outputProductService.getOne(id);
    }
    @PutMapping("/{id}")
    public Result editOutputProductController(@PathVariable Integer id,@RequestBody OutputProductDto dto){
        return outputProductService.editOutputProduct(id,dto);
    }
    @DeleteMapping("/{id}")
    public Result deleteOutputProductController(@PathVariable Integer id){
        return outputProductService.deleteOutputProduct(id);
    }
}
