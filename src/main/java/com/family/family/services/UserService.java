package com.family.family.services;

import com.family.family.dto.UserDTO;
import com.family.family.model.User;
import com.family.family.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<UserDTO> users(){
        return userRepo.findAllUsers().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void delete(Long id){
        userRepo.deleteById(id);
    }

    public UserDTO addOrUpdate(UserDTO userDTO){
        return convertToDTO(findById(save(convertToUser(userDTO))));
    }

    public Long save(User user){
        userRepo.save(user);
        return user.getId();
    }

    public User findById(Long id){
        return userRepo.findById(id).orElseThrow(()->new RuntimeException());
    }

    public List<UserDTO> filter(Integer a, Integer b){
        return userRepo.filterByAge(a, b).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO convertToDTO(User user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge());
    }

    public User convertToUser(UserDTO userDTO){
        User user = null;
        
        if (userDTO.getId() == null){
            user = new User();
        } else {
            user = findById(userDTO.getId());
        }

        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setAge(userDTO.getAge());
        return user;
    }

}
