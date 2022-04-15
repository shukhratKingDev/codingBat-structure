package com.company.coding_com.controller;

import com.company.coding_com.dto.ProblemDto;
import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.UserDto;
import com.company.coding_com.entity.Problem;
import com.company.coding_com.entity.User;
import com.company.coding_com.service.ProblemService;
import com.company.coding_com.service.UserService;
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
@RequestMapping("/api/problems")
public class ProblemController {
    ProblemService problemService;
@Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Problem>> getListOfProblems(){
       return ResponseEntity.status(HttpStatus.OK).body(problemService.getListOfProblems()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Problem>> getById(@PathVariable Integer id){
    Optional<Problem> problem=problemService.getById(id);
       return ResponseEntity.status(problem.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(problem);
    }


    @PostMapping("/add")
    public ResponseEntity<Response> addProblem( @Valid @RequestBody ProblemDto problemDto){
    Response response= problemService.addProblem(problemDto);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProblem(@Valid @RequestBody ProblemDto problemDto,@PathVariable Integer id){
    Response  response=problemService.updateProblem(problemDto,id);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProblem(@PathVariable Integer id){
    Response response=problemService.deleteUser(id);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
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
