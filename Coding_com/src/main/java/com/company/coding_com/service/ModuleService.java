package com.company.coding_com.service;

import com.company.coding_com.dto.ModuleDto;
import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.ThemeDto;
import com.company.coding_com.entity.Module;
import com.company.coding_com.entity.Problem;
import com.company.coding_com.entity.Theme;
import com.company.coding_com.repository.ModuleRepository;
import com.company.coding_com.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    ModuleRepository moduleRepository;
@Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> getListOfModules(){
        return moduleRepository.findAll();
    }

    public Optional<Module> getById(Integer id){
    return moduleRepository.findById(id);
    }

    public Response addModule(ModuleDto moduleDto){
    boolean exists=moduleRepository.existsByModuleName(moduleDto.getModuleName());
        if (exists) {
            return new Response("This module is already exist !!!",false);
        }
Module module=new Module();
moduleRepository.save(parse(moduleDto,module));

return new Response("Module successfully added",true);
    }

    public Response updateModule(ModuleDto moduleDto,Integer id){
    boolean exists=moduleRepository.existsByModuleNameAndIdNot(moduleDto.getModuleName(),id);
        if (!exists) {
          return new Response("The module with this id not found", false);
        }
        Module module=moduleRepository.getById(id);

        moduleRepository.save(parse(moduleDto,module));
        return new  Response("Module successfully updated",true);

    }




    public Response deleteTheme(Integer id) {
    Optional<Module> theme=moduleRepository.findById(id);
        if (theme.isPresent()) {
            moduleRepository.deleteById(id);
            return new Response("Module successfully deleted" ,true);
        }
        return new Response("Module with this id not found",false);
    }


    public Module parse(ModuleDto moduleDto,Module module){
    module.setModuleName(moduleDto.getModuleName());
      List<Theme> modules=new ArrayList<>(moduleDto.getThemeList());
    module.setThemes(modules);
        return module;

    }
}
