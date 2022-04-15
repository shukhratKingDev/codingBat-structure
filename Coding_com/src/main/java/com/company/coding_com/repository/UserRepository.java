package com.company.coding_com.repository;

import com.company.coding_com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
boolean existsByEmail(String email);
boolean existsByEmailAndIdNot(String email, Integer id);
}
