package com.company.repository;

import com.company.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {
boolean existsByNameAndPhoneNumber(String name, String phoneNumber);
boolean existsByNameAndPhoneNumberAndIdNot(String name, String phoneNumber, Integer id);
}
