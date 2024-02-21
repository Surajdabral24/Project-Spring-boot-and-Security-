package com.example.peter.controller;

import com.example.peter.constant.UserConstant;
import com.example.peter.entity.User;
import com.example.peter.entity.UserDocuments;
import com.example.peter.entity.UserEducation;
import com.example.peter.service.UserEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EducationController {

    @Autowired
    private UserEducationService educationService;


    @PostMapping("/uploadEducation")
    public ResponseEntity<UserEducation> uploadEducation(@RequestParam("educationId") long educationId, @RequestParam("courseName") String courseName, @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, @RequestParam("user_id") long user_id, @RequestParam("describeExperience") String describeExperience, @RequestParam("skills") String skills) {
        byte[] fileData;
        byte[] fileData1;
        try {
            fileData = file.getBytes();
            fileData1 = file1.getBytes();

            UserEducation education = new UserEducation();
            education.setEducationId(educationId);
            education.setCourseName(courseName);
            education.setDescribeExperience(describeExperience);
            education.setSkills(skills);

            User user = new User();
            user.setId(user_id);
            education.setUser(user);

            UserEducation savedEducation = educationService.saveEducation(education, fileData, fileData1);
            return ResponseEntity.ok(savedEducation);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


    @DeleteMapping("/deleteEducations")
    public ResponseEntity<String> deleteEducations(@PathVariable("id") long id) {
        try {
            String str = this.educationService.deleteEducations(id);

            return ResponseEntity.status(HttpStatus.OK).body(str);
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(UserConstant.MESSAGE_205);
        }

    }
}
