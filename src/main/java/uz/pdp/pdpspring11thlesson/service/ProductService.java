package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Attachment;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Category;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Measurement;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Product;
import uz.pdp.pdpspring11thlesson.paylaod.ProductDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.AttachmentRepository;
import uz.pdp.pdpspring11thlesson.repository.CategoryRepository;
import uz.pdp.pdpspring11thlesson.repository.MeasurementRepository;
import uz.pdp.pdpspring11thlesson.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository  productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto){

        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists){
            return new Result("Bunday maxsulot ushbu kategoriyada mavjud",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new Result("Bunday kategoriya mavjud emas",false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()){
            return new Result("Bunnday rasm mavjud emas",false);
        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new Result("Bunday measurement mavjud emas",false);
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode("1");
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Maxsulot saqlandi ",true);


    }

    public Page<Product> getAllProducts(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,100);
        return  productRepository.findAll(pageable);
    }
    public Page<Product> getProductsByCategoryId(@PathVariable Integer id, @RequestParam int page){
        Pageable pageable = PageRequest.of(page,100);
        return productRepository.findAllByCategoryId(id,pageable);
    }
    public Result getProductById(@PathVariable Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {

            return new Result("Siz izlagan maxsulot : ",true,optionalProduct.get());
        }
        return new Result("Bunday maxsulot topilmadi",false);
    }
    public Result deleteProduct(@PathVariable Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return new Result("Maxsulot o'chirildi ",true);
        }
        return  new Result("Bunday maxsulot yo'q",false);
    }
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()){
            return new Result("Bunday maxsulot mavjud emas",false);
        }
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists){
            return new Result("Bunday maxsulot ushbu kategoriyada mavjud",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new Result("Bunday kategoriya mavjud emas",false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()){
            return new Result("Bunnday rasm mavjud emas",false);
        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new Result("Bunday measurement mavjud emas",false);
        }
        Product editingProduct = optionalProduct.get();
        editingProduct.setCategory(optionalCategory.get());
        editingProduct.setMeasurement(optionalMeasurement.get());
        editingProduct.setPhoto(optionalAttachment.get());
        editingProduct.setName(productDto.getName());
        editingProduct.setCode("15");
        productRepository.save(editingProduct);
        return new Result("Maxsulot ma'lumotlari o'zgaritirldi ",true);

    }

}
