package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Currency;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Input;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Supplier;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Warehouse;
import uz.pdp.pdpspring11thlesson.paylaod.InputDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.CurrencyRepository;
import uz.pdp.pdpspring11thlesson.repository.InputRepository;
import uz.pdp.pdpspring11thlesson.repository.SupplierRepository;
import uz.pdp.pdpspring11thlesson.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    UserService userService;

    public Result addInput(InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Bunday ombor mavjud emas",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()){
            return  new Result("Bunday supplier mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Input input = new Input();

        input.setDate(Timestamp.valueOf(LocalDateTime.now()));
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(userService.random());
        inputRepository.save(input);
        return new Result("Input DB ga saqlandi",true);

    }
    public Page<Input> getAll(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAll(pageable);
    }
    public Input getOne(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }
    public Result editInput(Integer id,InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()){
            return new Result("Bunday input topilmadi",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Bunday ombor mavjud emas",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()){
            return  new Result("Bunday supplier mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Input editingInput = optionalInput.get();
       editingInput.setDate(Timestamp.valueOf(LocalDateTime.now()));
       editingInput.setWarehouse(optionalWarehouse.get());
       editingInput.setSupplier(optionalSupplier.get());
       editingInput.setCurrency(optionalCurrency.get());
       editingInput.setFactureNumber(inputDto.getFactureNumber());
       inputRepository.save(editingInput);
       return new Result("Input ma'lumotlari o'zgartirildi",true);
    }
    public Result deleteInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            inputRepository.deleteById(id);
            return new Result("Input DB dan o'chirildi",true);
        }
        return new Result("Bunday Input mavjud emas",false);
    }
}
