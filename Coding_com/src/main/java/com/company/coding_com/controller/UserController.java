package com.company.coding_com.controller;

import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.UserDto;
import com.company.coding_com.entity.User;
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
@RequestMapping("/api/users")
public class UserController {
    UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getListOfUsers(){
       return ResponseEntity.status(HttpStatus.OK).body(userService.getListOfUsers()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Integer id){
    Optional<User> user=userService.getById(id);
       return ResponseEntity.status(user.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(user);
    }


    @PostMapping("/add")
    public ResponseEntity<Response> addUser( @Valid @RequestBody UserDto userDto){
    Response response= userService.addUser(userDto);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer id){
    Response  response=userService.updateUser(userDto,id);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Integer id){
    Response response=userService.deleteUser(id);
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
