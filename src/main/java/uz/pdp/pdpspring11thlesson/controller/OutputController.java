package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Output;
import uz.pdp.pdpspring11thlesson.paylaod.OutputDto;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.OutputService;

@RestController
@RequestMapping(value = "/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutputController(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }
    @GetMapping
    public Page<Output> getAllController(@RequestParam int page){
        return outputService.getAll(page);
    }
    @GetMapping("/{id}")
    public Output getOneController(@PathVariable Integer id){
        return outputService.getOne(id);
    }
    @PutMapping("/{id}")
    public Result editOutputController(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        return outputService.editOutput(id,outputDto);
    }
    @DeleteMapping("/{id}")
    public Result deleteOutputController(@PathVariable Integer id){
        return outputService.deleteOutput(id);
    }

}
