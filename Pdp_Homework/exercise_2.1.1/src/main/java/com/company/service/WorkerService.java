package com.company.service;

import com.company.entity.Address;
import com.company.entity.Company;
import com.company.entity.Department;
import com.company.entity.Worker;
import com.company.payload.*;

import com.company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    private WorkerRepository workerRepository;

@Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;

    }

    public Response addWorker(WorkerDto workerDto){

        boolean exists=workerRepository.existsByNameAndPhoneNumber(workerDto.getName(),workerDto.getPhoneNumber());
        if (exists) {
            return new Response("This worker is already exist",false);
        }
        Address address=new Address();
        address.setStreet(workerDto.getAddress().getStreet());
        address.setHomeNumber(workerDto.getAddress().getHomeNumber());


        Company company=new Company();
        company.setCorpName(workerDto.getDepartment().getCompany().getCorpName());
        company.setDirectorName(workerDto.getDepartment().getCompany().getDirectorName());
        company.setAddress(address);



        Department department=new Department();
        department.setName(workerDto.getDepartment().getName());
        department.setCompany(company);

        Worker worker=new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(address);
        worker.setDepartment(department);
        workerRepository.save(worker);

        return new Response("Worker successfully saved",true);

    }

    public Response updateWorker(WorkerDto workerDto,Integer id){
boolean exists =workerRepository.existsByNameAndPhoneNumberAndIdNot(workerDto.getName(),workerDto.getPhoneNumber(),id);
Optional<Worker> worker=workerRepository.findById(id);
        if (!exists) {
            return new Response("The user not found",false);
        }

        Worker worker1=worker.get();

        Address address=new Address();
        address.setStreet(workerDto.getAddress().getStreet());
        address.setHomeNumber(workerDto.getAddress().getHomeNumber());


        Company company=new Company();
        company.setCorpName(workerDto.getDepartment().getCompany().getCorpName());
        company.setDirectorName(workerDto.getDepartment().getCompany().getDirectorName());
        company.setAddress(address);



        Department department=new Department();
        department.setName(workerDto.getDepartment().getName());
        department.setCompany(company);


        worker1.setName(workerDto.getName());
        worker1.setPhoneNumber(workerDto.getPhoneNumber());
        worker1.setAddress(address);
        worker1.setDepartment(department);
        workerRepository.save(worker1);

        return new Response("Worker successfully updated",true);



    }

    public List<Worker> getListOfWorkers(){
    return workerRepository.findAll();
    }
    public Optional<Worker> getById(Integer id){
    return workerRepository.findById(id);
    }

    public Response deleteWorker(Integer id){
    Optional<Worker> worker=workerRepository.findById(id);
        if (!worker.isPresent()) {
          return new Response("The worker with this id not found",false) ;
        }
        workerRepository.deleteById(id);
        return new Response("The worker successfully deleted",true);
    }
}
