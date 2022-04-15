package com.company.coding_com.service;

import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.ThemeDto;
import com.company.coding_com.dto.UserDto;
import com.company.coding_com.entity.Problem;
import com.company.coding_com.entity.Theme;
import com.company.coding_com.entity.User;
import com.company.coding_com.repository.ThemeRepository;
import com.company.coding_com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    ThemeRepository themeRepository;
@Autowired
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> getListOfThemes(){
        return themeRepository.findAll();
    }

    public Optional<Theme> getById(Integer id){
    return themeRepository.findById(id);
    }

    public Response addTheme(ThemeDto themeDto){
    boolean exists=themeRepository.existsByName(themeDto.getName());
        if (exists) {
            return new Response("The theme is already exist !!!",false);
        }
Theme theme=new Theme();
themeRepository.save(parse(themeDto,theme));

return new Response("Theme successfully added",true);
    }

    public Response updateTheme(ThemeDto themeDto,Integer id){
    boolean exists=themeRepository.existsByNameAndIdNot(themeDto.getName(),id);
        if (!exists) {
          return new Response("This theme with this id not found", false);
        }
        Theme theme=themeRepository.getById(id);

        themeRepository.save(parse(themeDto,theme));
        return new  Response("Theme successfully updated",true);

    }




    public Response deleteTheme(Integer id) {
    Optional<Theme> theme=themeRepository.findById(id);
        if (theme.isPresent()) {
            themeRepository.deleteById(id);
            return new Response("Theme successfully deleted" ,true);
        }
        return new Response("Theme with this id not found",false);
    }


    public Theme parse(ThemeDto themeDto,Theme theme){
        theme.setName(themeDto.getName());
        theme.setProblems(themeDto.getProblems());
      List<Problem> themes=new ArrayList<>(themeDto.getProblems());
     theme.setProblems(themes);
        return theme;

    }
}
