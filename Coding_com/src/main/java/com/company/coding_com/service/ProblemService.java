package com.company.coding_com.service;

import com.company.coding_com.dto.ProblemDto;
import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.UserDto;
import com.company.coding_com.entity.Problem;
import com.company.coding_com.entity.User;
import com.company.coding_com.repository.ProblemRepository;
import com.company.coding_com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    ProblemRepository problemRepository;
@Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> getListOfProblems(){
        return problemRepository.findAll();
    }

    public Optional<Problem> getById(Integer id){
    return problemRepository.findById(id);
    }

    public Response addProblem(ProblemDto problemDto){
    boolean exists=problemRepository.existsByNameAndTextOfProblem(problemDto.getName(),problemDto.getTextOfProblem());
        if (exists) {
            return new Response("This problem with this name is already exist !!!",false);
        }
Problem problem=new Problem();
problemRepository.save(parse(problemDto,problem));

return new Response("Problem successfully added",true);
    }

    public Response updateProblem(ProblemDto problemDto,Integer id){
    boolean exists=problemRepository.existsByNameAndTextOfProblemAndIdNot(problemDto.getName(),problemDto.getTextOfProblem(),id);
        if (!exists) {
          return new Response("The problem with this id  and text not found", false);
        }
        Problem problem=problemRepository.getById(id);

        problemRepository.save(parse(problemDto,problem));
        return new  Response("Problem successfully updated",true);

    }

    public Problem parse(ProblemDto problemDto,Problem problem){
   problem.setName(problemDto.getName());
   problem.setTextOfProblem(problemDto.getTextOfProblem());
   problem.setAnswer(problemDto.getAnswer());
   problem.setSolutionVideoLink(problemDto.getSolutionVideoLink());
    return problem;

    }


    public Response deleteUser(Integer id) {
    Optional<Problem> user=problemRepository.findById(id);
        if (user.isPresent()) {
            problemRepository.deleteById(id);
            return new Response("Problem successfully deleted" ,true);
        }
        return new Response("Problem with this id not found",false);
    }
}
