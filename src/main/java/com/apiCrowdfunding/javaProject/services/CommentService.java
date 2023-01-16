package com.apiCrowdfunding.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiCrowdfunding.javaProject.models.Comment;
import com.apiCrowdfunding.javaProject.repository.CommentRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }
}