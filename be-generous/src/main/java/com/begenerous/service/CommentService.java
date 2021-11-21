package com.begenerous.service;

import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(Comment comment, Long userId,  Long charityId) throws RowNotFoundException;

    List<Comment> getCommentsCharity(Long charityId) throws RowNotFoundException;

    List<Comment> getCommentsUser(Long userId) throws RowNotFoundException;
}
