package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Supplier;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.SupplierService;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplierController(@RequestBody  Supplier supplier){
        return supplierService.addSupplier(supplier);
    }
    @GetMapping
    public Page<Supplier> getAllController(@RequestParam int page){
        return supplierService.getSuppliers(page);
    }
    @GetMapping("/{id}")
    public Supplier getSupplierController(@PathVariable Integer id){
        return supplierService.getSupplier(id);
    }
    @PutMapping("/{id}")
    public Result editSupplierController(@PathVariable Integer id,@RequestBody Supplier supplier){
        return supplierService.editSupplier(id,supplier);
    }
    @DeleteMapping("/{id}")
    public  Result deleteSupplierController(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }

}
