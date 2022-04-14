package com.company.service;

import com.company.entity.Address;
import com.company.entity.Company;
import com.company.payload.CompanyDto;
import com.company.payload.Response;
import com.company.repository.AddressRepository;
import com.company.repository.CompanyRepository;
import com.company.repository.DepartmentRepository;
import com.company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private  CompanyRepository companyRepository;
@Autowired
    public CompanyService( CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

public List<Company> getListOfCompanies(){
    return companyRepository.findAll();
}

public Optional<Company> getCompanyById(Integer id){
    return companyRepository.findById(id);
}

public Response addCompany(CompanyDto companyDto){
    boolean exists=companyRepository.existsByCorpName(companyDto.getCorpName());
    if(exists){
        return new Response("The company with this name and address have already exists",false);
    }
    Company company=new Company();
    company.setCorpName(companyDto.getCorpName());
    company.setDirectorName(companyDto.getDirectorName());
    Address address=new Address();
    address.setStreet(companyDto.getAddress().getStreet());
    address.setHomeNumber(companyDto.getAddress().getHomeNumber());
    company.setAddress(address);
    companyRepository.save(company);
    return new Response("Company successfully saved !!!",true);
}

public Response updateCompany(CompanyDto companyDto ,Integer id){
    boolean exists=companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(),id);
if (!exists){
    return new Response("The company with this name and address not found !!!",false);
}
    Address address=new Address();
    address.setHomeNumber(companyDto.getAddress().getHomeNumber());
    address.setStreet(companyDto.getAddress().getStreet());
Company company=new Company();
company.setCorpName(companyDto.getCorpName());
company.setDirectorName(companyDto.getDirectorName());
company.setAddress(address);
companyRepository.save(company);

    return new Response("Company successfully updated",true);
}

public Response deleteCompany(Integer id){
    Optional<Company> company=companyRepository.findById(id);
    if (company.isPresent()){
        companyRepository.deleteById(id);
        return new Response("Company successfully deleted",true);
    }
    return new Response("Company with this id not found",false);
}
}
