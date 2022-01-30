package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.User;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.paylaod.UserDto;
import uz.pdp.pdpspring11thlesson.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result addUserController(@RequestBody UserDto user){
        return userService.addUser(user);
    }
    @GetMapping
    public List<User> getAllController(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public User getOneController(@PathVariable Integer id){
        return userService.getOne(id);
    }
    @PutMapping("/{id}")
    public Result editUserController(@PathVariable Integer id,@RequestBody UserDto userDto){
        return userService.editUser(id,userDto);
    }
    @DeleteMapping("/{id}")
    public Result deleteUserController(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

}
