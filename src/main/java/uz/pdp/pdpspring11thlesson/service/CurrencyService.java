package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Currency;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency){
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName){
            return new Result("Bunday valyuta mavjud",false);
        }
        currencyRepository.save(currency);
        return new Result("Valyuta saqlandi",true);
    }

    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }
    public Result editCurrency(Integer id,Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty()){
            return new Result("Bunday valyuta topilmadi",false);
        }
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName){
            return new Result("Bunday valyuta mavjud",false);
        }
        Currency editingCurrency = optionalCurrency.get();
        editingCurrency.setName(currency.getName());
        currencyRepository.save(editingCurrency);
        return new Result("Valyuta ma'lumotlari o'zgartirildi",true);

    }
    public Result deleteCurrency(Integer id){
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isPresent()){
            currencyRepository.deleteById(id);
            return new Result("Valyuta o'chirildi",true);
        }
        return new Result("Bunday valyuta topilmadi",false);
    }

}
