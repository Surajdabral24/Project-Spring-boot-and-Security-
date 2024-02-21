package com.example.peter.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudConfig {

    @Bean
    public Cloudinary getCloudinary(){
        Map map = new HashMap();
        map.put("cloud_name","dmjeptp4l");
        map.put("api_key","915471537847153");
        map.put("api_secret","xojBbfhzL9kX8FEP6ZxTgvnfTOg");
        map.put("secure",true);

        return new Cloudinary(map);
    }
}
