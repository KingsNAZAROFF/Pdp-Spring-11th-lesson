package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Output;
import uz.pdp.pdpspring11thlesson.paylaod.entity.OutputProduct;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Product;
import uz.pdp.pdpspring11thlesson.paylaod.OutputProductDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.OutputProductRepository;
import uz.pdp.pdpspring11thlesson.repository.OutputRepository;
import uz.pdp.pdpspring11thlesson.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProduct(OutputProductDto dto){

        Optional<Output> optionalOutput = outputRepository.findById(dto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new Result("Bunday Output mavjud emas",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Bunday product mavjud emas",false);
        }
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setPrice(dto.getPrice());
        outputProduct.setAmount(dto.getAmount());
        outputProductRepository.save(outputProduct);
        return new Result("OutputProduct DB ga saqlandi",true);
    }
    public Page<OutputProduct> getAll(int page){
        Pageable pageable = PageRequest.of(page, 10);

        return outputProductRepository.findAll(pageable);
    }
    public OutputProduct getOne(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElseGet(OutputProduct::new);
    }
    public Result editOutputProduct(Integer id,OutputProductDto dto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty()){
            return new Result("Bunday outputProduct mavjud emas",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(dto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new Result("Bunday Output mavjud emas",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if (optionalProduct.isEmpty()){
            return new Result("Bunday product mavjud emas",false);
        }
        OutputProduct editingOutputProduct = optionalOutputProduct.get();
        editingOutputProduct.setOutput(optionalOutput.get());
        editingOutputProduct.setProduct(optionalProduct.get());
        editingOutputProduct.setPrice(dto.getPrice());
        editingOutputProduct.setAmount(dto.getAmount());
        outputProductRepository.save(editingOutputProduct);
        return new Result("OutputProduct ma'lumotlari o'zgartirildi",true);
    }
    public Result deleteOutputProduct(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty()){
            return new Result("Bunday outputProduct mavjud emas",false);
        }
        outputProductRepository.deleteById(id);
        return new Result("outputProduct DB dan o'chirildi",true);
    }
}
