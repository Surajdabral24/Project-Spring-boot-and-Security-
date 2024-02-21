package com.example.peter.service;

import com.example.peter.dto.UserDto;
import com.example.peter.emun.Status;
import com.example.peter.entity.User;
import com.example.peter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder encoder;





    public List<UserDto> readAllUser() {

        List<User> listOfUser = this.userRepository.findAll();
        return listOfUser.stream().map(this::convertEntityToDto).collect(Collectors.toList());

    }

    public UserDto readUserById(long id){
        User user = this.userRepository.findById(id).orElseThrow(null);
        return convertEntityToDto(user);
    }


    public String deleteUserById(long id){
        User user = this.userRepository.findById(id).orElseThrow(null);
        if(user == null){

            return "user does not exists";
        }
        else{

            user.setStatus(Status.BLOCK);
            this.userRepository.save(user);
            return "user details deleted";

        }

    }

    public String updateUserById(User user,long id){

        User userInfo = this.userRepository.findById(id).orElseThrow(null);
        if(userInfo == null){
            return "User not registered yet of that Id....";
        }else if(userInfo.equals(Status.BLOCK)) {
            return "user deleted";
        }else {
            userInfo.setId(user.getId());
            userInfo.setFirstname(user.getFirstname());
            userInfo.setLastname(user.getLastname());
            userInfo.setEmail(user.getEmail());
            userInfo.setPassword(encoder.encode(user.getPassword()));
            userInfo.setPhoneNumber(user.getPhoneNumber());
            userInfo.setGender(user.getGender());
            userInfo.setRole(user.getRole());
            userInfo.setStatus(Status.UPDATE);
            userInfo.setUserDocuments(user.getUserDocuments());
            userInfo.setUserEducation(user.getUserEducation());
            this.userRepository.save(userInfo);

            return "user details updated";

        }

        }



    public UserDto convertEntityToDto(User user) {

        UserDto dto = this.mapper.map(user,UserDto.class);
//        dto.setFirstname(user.getFirstname());
//        dto.setLastname(user.getLastname());
//        dto.setEmail(user.getEmail());
//        dto.setPhoneNumber(user.getPhoneNumber());
//        dto.setGender(user.getGender());
//        dto.setRole(user.getRole());
//        dto.setStatus(user.getStatus());
        return dto;
    }

}
