package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Supplier;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier){
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists){
            return new Result("Ushbu telefon raqam oldinham ro'yxatdan o'tkazilgan",false);
        }
        Supplier supplier1 = new Supplier();
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplier1.setName(supplier.getName());
        supplierRepository.save(supplier1);
        return new Result("supplier DB ga saqlandi",true);
    }
    public Page<Supplier> getSuppliers(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return supplierRepository.findAll(pageable);
    }
    public Supplier getSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElseGet(Supplier::new);
    }

    public Result editSupplier(Integer id,Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()){
            return new Result("Bunday supplier mavjud emas",false);
        }
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists){
            return new Result("Ushbu telefon raqam oldinham ro'yxatdan o'tkazilgan",false);
        }
        Supplier editingSupplier = optionalSupplier.get();
        editingSupplier.setName(supplier.getName());
        editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(editingSupplier);
        return new Result("Supplier ma'lumotlari o'zgartirildi",true);
    }

    public Result deleteSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()){
            supplierRepository.deleteById(id);
            return new Result("Supplier o'chirildi",true);
        }
        return new Result("Bunday supplier topilmadi",false);
    }
}
