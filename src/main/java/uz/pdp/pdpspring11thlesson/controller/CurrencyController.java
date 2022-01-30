package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Currency;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result addCurrencyController(@RequestBody Currency currency){
        return  currencyService.addCurrency(currency);
    }
    @GetMapping
    public List<Currency> getAllController(){
        return currencyService.getAll();
    }
    @PutMapping("/{id}")
    public Result editCurrencyController(@PathVariable Integer id,@RequestBody Currency currency){
        return currencyService.editCurrency(id,currency);
    }
    @DeleteMapping("/{id}")
    public Result deleteCurrencyController(@PathVariable Integer id){
        return currencyService.deleteCurrency(id);
    }

}
