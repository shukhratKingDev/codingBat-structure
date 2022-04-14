package com.company.service;

import com.company.entity.Address;
import com.company.entity.Company;
import com.company.entity.Department;
import com.company.payload.DepartmentDto;
import com.company.payload.Response;
import com.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;
@Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;

    }

    public List<Department> getListOfDepartments(){
    return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Integer id){
    return departmentRepository.findById(id);
    }

    public Response deleteDepartment(Integer id){
    Optional<Department> department=departmentRepository.findById(id);
        if (department.isPresent()) {
            return new Response("Department successfully deleted !!!",true);
        }
        return new Response("Department with this id not found !!!",false);
    }

    public Response addDepartment(DepartmentDto departmentDto){
    Optional<Department> department1=departmentRepository.findByNameAndCompany_CorpName(departmentDto.getName(),departmentDto.getCompany().getCorpName());
        if (department1.isPresent()) {
            return new Response("This department has already exists in this company",false);
        }
        Address address=new Address();
        address.setStreet(departmentDto.getCompany().getAddress().getStreet());
        address.setHomeNumber(departmentDto.getCompany().getAddress().getHomeNumber());
        Company company=new Company();
        company.setCorpName(departmentDto.getCompany().getCorpName());
        company.setDirectorName(departmentDto.getCompany().getDirectorName());
        company.setAddress(address);
       Department department=new Department();
        department.setName(departmentDto.getName());
        department.setCompany(departmentDto.getCompany());
        departmentRepository.save(department);
        return new Response("Department successfully added",true);
    }

    public Response updateDepartment(DepartmentDto departmentDto,Integer id){
    boolean exists=departmentRepository.existsByNameAndCompany_CorpName(departmentDto.getName(),departmentDto.getCompany().getCorpName());
        Optional<Department> department1=departmentRepository.findById(id);
        if (!exists) {
            return new Response("The department in this company not found",false);

        }

        Address address=new Address();
        address.setStreet(departmentDto.getCompany().getAddress().getStreet());
        address.setHomeNumber(departmentDto.getCompany().getAddress().getHomeNumber());
        Company company=new Company();
        company.setCorpName(departmentDto.getCompany().getCorpName());
        company.setDirectorName(departmentDto.getCompany().getDirectorName());
        company.setAddress(address);
        Department department=department1.get();
        department.setCompany(company);
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new Response("Department successfully updated",true);
    }
}
