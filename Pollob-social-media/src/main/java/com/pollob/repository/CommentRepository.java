package com.pollob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pollob.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>  {

}
