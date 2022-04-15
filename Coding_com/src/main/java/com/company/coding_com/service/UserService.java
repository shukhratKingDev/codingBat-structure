package com.company.coding_com.service;

import com.company.coding_com.dto.Response;
import com.company.coding_com.dto.UserDto;
import com.company.coding_com.entity.Problem;
import com.company.coding_com.entity.User;
import com.company.coding_com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getListOfUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id){
    return userRepository.findById(id);
    }

    public Response addUser(UserDto userDto){
    boolean exists=userRepository.existsByEmail(userDto.getEmail());
        if (exists) {
            return new Response("The user with this email is already exist !!!",false);
        }
User user=new User();
userRepository.save(parse(userDto,user));

return new Response("User successfully added",true);
    }

    public Response updateUser(UserDto userDto,Integer id){
    boolean exists=userRepository.existsByEmailAndIdNot(userDto.getEmail(),id);
        if (!exists) {
          return new Response("This user with this id  and email not found", false);
        }
        User user=userRepository.getById(id);

        userRepository.save(parse(userDto,user));
        return new  Response("User successfully updated",true);

    }

    public User parse(UserDto userDto,User user){
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setTotalScore(userDto.getTotalScore());
        List<Problem> problems = new ArrayList<>(userDto.getProblem());
    user.setSolvedProblems(problems);

    return user;

    }


    public Response deleteUser(Integer id) {
    Optional<User> user=userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return new Response("User successfully deleted" ,true);
        }
        return new Response("User with this id not found",false);
    }
}
