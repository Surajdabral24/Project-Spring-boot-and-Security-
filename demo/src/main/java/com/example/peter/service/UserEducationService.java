package com.example.peter.service;

import com.example.peter.entity.UserDocuments;
import com.example.peter.entity.UserEducation;
import com.example.peter.repository.UserEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEducationService {

    @Autowired
    private UserEducationRepository educationRepository;

    @Autowired
    private CloudService cloudService;

    public UserEducation saveEducation(UserEducation education, byte[] fileData, byte[] fileData1) {

        String filename = education.getCertificateUrl() + System.currentTimeMillis();
        String filename1 = education.getCvUrl() + System.currentTimeMillis();

        String fileUrl = cloudService.uploadFile(fileData, filename);
        String fileUrl1 = cloudService.uploadFile(fileData1, filename1);


        education.setCertificateUrl(fileUrl);
        education.setCvUrl(fileUrl1);

        educationRepository.save(education);
        return education;
    }


    public String deleteEducations(long id) {

        UserEducation education = this.educationRepository.findByUserId(id);
        if (education == null) {
            return "Educations not uploaded yet....";
        } else {
            this.educationRepository.deleteById(education.getEducationId());
            return "Educations deleted successfully";
        }

    }
}
