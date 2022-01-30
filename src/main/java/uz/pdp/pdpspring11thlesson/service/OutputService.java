package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.entity.*;
import uz.pdp.pdpspring11thlesson.paylaod.OutputDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Client;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Currency;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Output;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Warehouse;
import uz.pdp.pdpspring11thlesson.repository.ClientRepository;
import uz.pdp.pdpspring11thlesson.repository.CurrencyRepository;
import uz.pdp.pdpspring11thlesson.repository.OutputRepository;
import uz.pdp.pdpspring11thlesson.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserService userService;

    public Result addOutput(OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Bunday ombor mavjud emas",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new Result("Bunday Client mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Output output = new Output();
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setCode(userService.random());
        output.setDate(output.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result("Output DB ga saqlandi",true);


    }
    public Page<Output> getAll(int page){
        Pageable pageable = PageRequest.of(page, 10);

        return outputRepository.findAll(pageable);
    }
    public Output getOne(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }
    public Result editOutput(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()){
            return new Result("Bunday output mavjud emas",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Bunday ombor mavjud emas",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new Result("Bunday Client mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Output editingOutput = optionalOutput.get();
        editingOutput.setClient(optionalClient.get());
        editingOutput.setCurrency(optionalCurrency.get());
        editingOutput.setWarehouse(optionalWarehouse.get());
        editingOutput.setDate(outputDto.getDate());
        editingOutput.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(editingOutput);
        return new Result("Output ma'lumotlari o'zgartirildi",true);
    }
    public Result deleteOutput(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) {
            return new Result("Bunday output mavjud emas", false);
        }
        outputRepository.deleteById(id);
        return new Result("Output Db dan o'chirildi", true);
    }
    }
