package com.company.coding_com.controller;

import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.ThemeDto;
import com.company.coding_com.dto.UserDto;
import com.company.coding_com.entity.Theme;
import com.company.coding_com.entity.User;
import com.company.coding_com.service.ThemeService;
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
@RequestMapping("/api/themes")
public class ThemeController {
   ThemeService themeService;
@Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Theme>> getListOfThemes(){
       return ResponseEntity.status(HttpStatus.OK).body(themeService.getListOfThemes()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Theme>> getThemeById(@PathVariable Integer id){
    Optional<Theme> theme=themeService.getById(id);
       return ResponseEntity.status(theme.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(theme);
    }


    @PostMapping("/add")
    public ResponseEntity<Response> addTheme( @Valid @RequestBody ThemeDto themeDto){
    Response response= themeService.addTheme(themeDto);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTheme(@Valid @RequestBody ThemeDto themeDto,@PathVariable Integer id){
    Response  response=themeService.updateTheme(themeDto,id);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTheme(@PathVariable Integer id){
    Response response=themeService.deleteTheme(id);
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
