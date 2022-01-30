package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Measurement;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;
    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return  result;

    }

    @GetMapping
    public List<Measurement> getALlController(){
        return measurementService.getAll();
    }

    @PutMapping("/{id}")
    public Result editMeasurementController(@PathVariable Integer id,@RequestBody Measurement measurement){
        return  measurementService.editMeasurement(id, measurement);

    }
    @DeleteMapping("/{id}")
    public Result deleteMeasurementController(@PathVariable Integer id){
      return   measurementService.deleteMeasurement(id);
    }
}
