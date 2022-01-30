package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Warehouse;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouseController(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }
    @GetMapping
    public List<Warehouse> getALlController(){
        return warehouseService.getAll();
    }

    @GetMapping("/{id}")
    public Warehouse getOneController(@PathVariable Integer id){
        return warehouseService.getOne(id);
    }
    @PutMapping("/{id}")
    public Result editWarehouseController(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.editWarehouse(id,warehouse);
    }
    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        return warehouseService.deleteWarehouse(id);
    }
}
