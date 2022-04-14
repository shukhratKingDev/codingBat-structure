package com.company.controller;

import com.company.entity.Department;
import com.company.payload.DepartmentDto;
import com.company.payload.Response;
import com.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    DepartmentService departmentService;
@Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public HttpEntity<List<Department>> getListOfDepartments(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getListOfDepartments());
    }

    @GetMapping("/{id}")
    public HttpEntity<Optional<Department>> getDepartmentById(@PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartmentById(id));

    }


    @PostMapping("/add")
    public HttpEntity<Response> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
    Response response=departmentService.addDepartment(departmentDto);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<Response> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto, @PathVariable Integer id){
        Response response=departmentService.updateDepartment(departmentDto,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Response> deleteDepartment(@PathVariable Integer id){
    Response response=departmentService.deleteDepartment(id);
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
