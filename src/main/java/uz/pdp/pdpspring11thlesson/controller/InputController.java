package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Input;
import uz.pdp.pdpspring11thlesson.paylaod.InputDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.InputService;

@RestController
@RequestMapping(value = "/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInputController(InputDto inputDto){
        return inputService.addInput(inputDto);
    }
    @GetMapping
    public Page<Input> getAllController(@RequestParam int page){
        return inputService.getAll(page);
    }
    @GetMapping("/{id}")
    public Input getOneController(@PathVariable Integer id){
        return inputService.getOne(id);
    }
    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id,@RequestBody InputDto inputDto){
        return inputService.editInput(id,inputDto);
    }
    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }
}
