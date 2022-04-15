package com.company.coding_com.repository;

import com.company.coding_com.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {
    boolean existsByNameAndIdNot(String name,Integer id);
    boolean existsByName(String name);
}
