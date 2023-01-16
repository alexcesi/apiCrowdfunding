package com.apiCrowdfunding.javaProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiCrowdfunding.javaProject.models.Comment;

@Repository
public interface CommentRepository extends  JpaRepository<Comment, Integer>{

}
