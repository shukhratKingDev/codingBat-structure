package com.company.repository;

import com.company.entity.Company;
import com.company.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department>  findByNameAndCompany_CorpName(String name, String company_corpName);
boolean existsByNameAndCompany_CorpName(String name, String company_corpName);
}
