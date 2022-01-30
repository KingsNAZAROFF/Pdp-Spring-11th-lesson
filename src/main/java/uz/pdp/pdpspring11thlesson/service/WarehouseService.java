package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Warehouse;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service


public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse){
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName){
            return new Result("Bunday ombor mavjud",false);
        }
        warehouseRepository.save(warehouse);
        return new Result("Ombor saqlandi",true);
    }

    public List<Warehouse> getAll(){
        return warehouseRepository.findAll();
    }
    public Warehouse getOne(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElseGet(Warehouse::new);
    }

    public Result editWarehouse(Integer id,Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()){
            return new Result("Bunday nomli ombor mavjud emas",false);
        }

        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName){
            return new Result("Bunday nomili ombor mavjud",false);
        }
        Warehouse editingWarehouse = optionalWarehouse.get();
        editingWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editingWarehouse);
        return new Result("Ombor ma'lumotlari o'zgartirildi",true);
    }
    public Result deleteWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()){
            warehouseRepository.deleteById(id);
            return new Result("Ombor DB dan o'chirildi",true);
        }
        return new Result("Bunday ombor mavjud emas",false);
    }
}
