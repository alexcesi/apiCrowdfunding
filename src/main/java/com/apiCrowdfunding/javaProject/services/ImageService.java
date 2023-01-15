package com.apiCrowdfunding.javaProject.services;

import java.io.IOException;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apiCrowdfunding.javaProject.models.Image;

import com.apiCrowdfunding.javaProject.repository.ImageRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image add(MultipartFile file) {
        try {
		  byte[] imageBytes = file.getBytes();
	      String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
	      Image image = new Image();
          image.setImageUrl(encodedImage);
          return imageRepository.save(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    

}