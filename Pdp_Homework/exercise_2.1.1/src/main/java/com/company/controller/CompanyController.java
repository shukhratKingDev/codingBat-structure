package com.company.controller;

import com.company.entity.Company;
import com.company.payload.AddressDto;
import com.company.payload.CompanyDto;
import com.company.payload.Response;
import com.company.service.CompanyService;
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
@RequestMapping("/api/company")
public class CompanyController {
    private CompanyService service;
@Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Company>> getCompanyList(){
      return ResponseEntity.status(HttpStatus.OK).body(service.getListOfCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Company>> getCompanyById(@PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.OK).body(service.getCompanyById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addCompany( @Valid @RequestBody  CompanyDto companyDto){
    Response response=service.addCompany(companyDto);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCompany(  @Valid  @RequestBody CompanyDto companyDto,  @PathVariable Integer id){
Response response=service.updateCompany(companyDto,id);
return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCompany(@PathVariable Integer id){
    Response response=service.deleteCompany(id);
    return ResponseEntity.status(response.isSuccess()?200:409).body(response);
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
