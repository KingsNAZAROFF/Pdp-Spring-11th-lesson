package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.User;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Warehouse;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.paylaod.UserDto;
import uz.pdp.pdpspring11thlesson.repository.UserRepository;
import uz.pdp.pdpspring11thlesson.repository.WarehouseRepository;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUser(UserDto userDto){

        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("Telefon raqam ro'yxatdan o'tkazilgan",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Bunday ombor mavjud emas",false);
        }
        User user = new User();
        Set<Warehouse> warehouseSet = new HashSet<>();
        warehouseSet.add(optionalWarehouse.get());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(random());
        user.setPassword(userDto.getPassword());
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new Result("User DB ga saqlandi",true);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getOne(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }
    public Result editUser(Integer id,UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new Result("Bunday User mavjud emas",false);
        }
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("Telefon raqam ro'yxatdan o'tkazilgan",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()){
            return new Result("Bunday ombor mavjud emas",false);
        }
        User editingUser = optionalUser.get();
        Set<Warehouse> warehouseSet = new HashSet<>();
        warehouseSet.add(optionalWarehouse.get());
        editingUser.setFirstName(userDto.getFirstName());
        editingUser.setLastName(userDto.getLastName());
        editingUser.setPhoneNumber(userDto.getPhoneNumber());
        editingUser.setPassword(userDto.getPassword());
        editingUser.setWarehouses(warehouseSet);
        userRepository.save(editingUser);
        return new Result("User ma'lumotlari o'zgartirildi",true);

    }
    public Result deleteUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return new Result("User o'chirildi",true);
        }
        return new Result("Bunday user mavjud emas",false);
    }


















    public String  random(){
        int max = 100;
        int min = 10;
        int randomInt = new SecureRandom().nextInt(max - min) + min;
        return String.valueOf(randomInt);

    }
}
