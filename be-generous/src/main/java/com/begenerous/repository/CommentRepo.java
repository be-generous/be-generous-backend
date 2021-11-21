package com.begenerous.repository;

import com.begenerous.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM COMMENT WHERE USER_ID = ?1", nativeQuery = true)
    List<Comment> findAllByUserId(Long userId);

    @Query(value = "SELECT * FROM COMMENT WHERE CHARITY_ID = ?1", nativeQuery = true)
    List<Comment> findAllByCharity(Long charityId);
}
