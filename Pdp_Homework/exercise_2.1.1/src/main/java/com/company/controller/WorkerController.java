package com.company.controller;

import com.company.entity.Worker;
import com.company.payload.Response;
import com.company.payload.WorkerDto;
import com.company.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    WorkerService workerService;
@Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addWorker(@Valid @RequestBody WorkerDto workerDto){
        Response response=workerService.addWorker(workerDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateWorker(@Valid @RequestBody WorkerDto workerDto, @PathVariable Integer id){
    Response response=workerService.updateWorker(workerDto,id);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Worker>> getListOfWorkers(){
    return ResponseEntity.status(HttpStatus.OK).body(workerService.getListOfWorkers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Worker>> getById(@PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.OK).body(workerService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteWorker(@PathVariable Integer id){
    Response response=workerService.deleteWorker(id);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException validException){
        Map<String,String> errors=new HashMap<>();
        validException.getBindingResult().getAllErrors().forEach((error )-> {
            String fieldName=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
}
