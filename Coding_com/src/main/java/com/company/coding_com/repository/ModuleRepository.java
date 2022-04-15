package com.company.coding_com.repository;

import com.company.coding_com.entity.Module;
import com.company.coding_com.entity.model.ModuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer> {
boolean existsByModuleName(ModuleName moduleName);
boolean existsByModuleNameAndIdNot(ModuleName moduleName, Integer id);
}
