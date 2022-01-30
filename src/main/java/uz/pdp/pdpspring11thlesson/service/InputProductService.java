package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Input;
import uz.pdp.pdpspring11thlesson.paylaod.entity.InputProduct;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Product;
import uz.pdp.pdpspring11thlesson.paylaod.InputProductDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.InputProductRepository;
import uz.pdp.pdpspring11thlesson.repository.InputRepository;
import uz.pdp.pdpspring11thlesson.repository.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addInputProduct(InputProductDto inputProductDto){
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()){
            return new Result("Bunday input topilmadi",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Bunday product topilmadi",false);
        }
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("InputProduct DB ga saqlandi",true);
    }
    public Page<InputProduct> getAll(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return inputProductRepository.findAll(pageable);

    }
    public InputProduct getOne(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElseGet(InputProduct::new);
    }
    public Result editInputProduct(Integer id,InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()){
            return new Result("Bunday inputProduct mavjud emas",true);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()){
            return new Result("Bunday input topilmadi",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Bunday product topilmadi",false);
        }

        InputProduct editingInputProduct = optionalInputProduct.get();
           editingInputProduct.setProduct(optionalProduct.get());
           editingInputProduct.setInput(optionalInput.get());
           editingInputProduct.setAmount(inputProductDto.getAmount());
           editingInputProduct.setPrice(inputProductDto.getPrice());
           editingInputProduct.setExpireDate(inputProductDto.getExpireDate());
           inputProductRepository.save(editingInputProduct);
           return new Result("InputProduct ma'lumotlari o'zgartirildi",true);

    }
    public Result deleteInputProduct(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()){
            inputProductRepository.deleteById(id);
            return new Result("Input product DB dan o'chirildi",true);
        }
        return new Result("Bunday inputProduct mavjud emas",false);
    }

}
