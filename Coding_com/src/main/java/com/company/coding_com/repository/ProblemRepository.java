package com.company.coding_com.repository;

import com.company.coding_com.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Integer> {
    boolean existsByNameAndTextOfProblem(String name, String textOfProblem);
boolean existsByNameAndTextOfProblemAndIdNot(String name, String textOfProblem, Integer id);
}
