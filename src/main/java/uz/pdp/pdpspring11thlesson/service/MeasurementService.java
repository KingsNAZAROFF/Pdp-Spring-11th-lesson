package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Measurement;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if(existsByName) {
            return new Result("Bunnday o'lchov birligi mavjud", false);
        }
        measurementRepository.save(measurement);
        return new Result("Muvaffaqiyatli saqlandi",true);
    }

    public List<Measurement> getAll(){
       return measurementRepository.findAll();
    }

    public Result editMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return new Result("Bunday measurement topilmadi",false);
        }
        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        return new Result("Muvaffaqiyatli o'zgaritirldi",true);
    }

    public Result deleteMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()){
            measurementRepository.deleteById(id);
            return new Result("Measurement o'chirildi",true);
        }
        return new Result("Bunday measurement topilmadi",false);
    }


}
