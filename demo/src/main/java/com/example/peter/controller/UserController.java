package com.example.peter.controller;

import com.example.peter.config.UserInfoUserDetails;
import com.example.peter.constant.UserConstant;
import com.example.peter.dto.AuthRequest;
import com.example.peter.dto.UserDto;
import com.example.peter.emun.Status;
import com.example.peter.entity.User;
import com.example.peter.filter.JwtService;
import com.example.peter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConstant userConstant;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;



//    @GetMapping("users/readById/{id}")
////    @PreAuthorize("hasAuthority('User')")
//    public ResponseEntity<UserDto> getById(@PathVariable long id){
//         try{
//             UserDto userDto = this.userService.readUserById(id);
//             if (userDto == null){
//                 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//             }else if (userDto.getStatus() == Status.BLOCK) {
//                 return new ResponseEntity("user is deleted",HttpStatus.GONE);
//             }else{
//                 return ResponseEntity.status(HttpStatus.OK).body(userDto);
//             }
//         }catch (Exception e){
//             e.printStackTrace();
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//         }
//    }

    @GetMapping("users/readById/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<UserDto> getById(@PathVariable long id,Authentication authentication){
        try{
            UserDto userDto = this.userService.readUserById(id);
            if (userDto == null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else if (userDto.getStatus() == Status.BLOCK) {
                return new ResponseEntity("user is deleted",HttpStatus.GONE);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(userDto);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("users/readAll")
    @PreAuthorize("hasAnyAuthority('Admin', 'Subadmin')")
    public ResponseEntity<List<UserDto>> getAll(){
        try{
            List<UserDto> list = this.userService.readAllUser();
            if(list.size() <= 0){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(list);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



//    @PostMapping("/generateToken")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("invalid user request !...");
//        }
//
//    }



    @PutMapping("users/updateUser/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable("id") long id,Authentication authentication){

        try{
            String str = this.userService.updateUserById(user,id);
            return ResponseEntity.status(HttpStatus.OK).body(str);
        }catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @DeleteMapping("users/deleteUser/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id,Authentication authentication){
        try{
            String str = this.userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(str);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userConstant.MESSAGE_205);
        }

    }



}
