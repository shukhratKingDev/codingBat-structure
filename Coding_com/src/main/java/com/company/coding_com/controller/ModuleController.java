package com.company.coding_com.controller;

import com.company.coding_com.dto.ModuleDto;
import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.ThemeDto;
import com.company.coding_com.entity.Module;
import com.company.coding_com.entity.Theme;
import com.company.coding_com.service.ModuleService;
import com.company.coding_com.service.ThemeService;
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
public class ModuleController {
   ModuleService moduleService;
@Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Module>> getListOfModules(){
       return ResponseEntity.status(HttpStatus.OK).body(moduleService.getListOfModules()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Module>> getModuleById(@PathVariable Integer id){
    Optional<Module> module=moduleService.getById(id);
       return ResponseEntity.status(module.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(module);
    }


    @PostMapping("/add")
    public ResponseEntity<Response> addModule( @Valid @RequestBody ModuleDto moduleDto){
    Response response= moduleService.addModule(moduleDto);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateModule(@Valid @RequestBody ModuleDto moduleDto,@PathVariable Integer id){
    Response  response=moduleService.updateModule(moduleDto,id);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteModule(@PathVariable Integer id){
    Response response=moduleService.deleteTheme(id);
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
