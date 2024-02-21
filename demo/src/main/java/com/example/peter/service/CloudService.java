package com.example.peter.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class CloudService {

    private final Cloudinary cloudinary;

    public CloudService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(byte[] fileData, String filename) {
            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, ObjectUtils.asMap("public_id", filename));
                System.out.print(uploadResult.get("url"));
                return (String) uploadResult.get("url");
            } catch (Exception e) {
                throw new RuntimeException("Failed to upload file to Cloudinary: " + e.getMessage(), e);
            }
        }

        public String getFileUrl(String filename) {
            return cloudinary.url().generate(filename);
        }
    }

