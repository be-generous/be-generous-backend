package com.begenerous.service.impl;

import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Charity;
import com.begenerous.model.Comment;
import com.begenerous.model.User;
import com.begenerous.repository.CharityRepo;
import com.begenerous.repository.CommentRepo;
import com.begenerous.repository.UserRepo;
import com.begenerous.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final CharityRepo charityRepo;

    @Override
    public Comment saveComment(Comment comment, Long userId, Long charityId) throws RowNotFoundException {
        if (!comment.getIsAnonymous()) {
            User user = userRepo.findById(userId).orElseThrow(() -> new RowNotFoundException("No user found with the id: " + userId));
            Charity charity = charityRepo.findById(charityId).orElseThrow(() -> new RowNotFoundException("No charity found with the id: " + charityId));

            comment.setUser(user);
            comment.setCharity(charity);
        }
        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> getCommentsCharity(Long charityId) throws RowNotFoundException {
        List<Comment> comments = commentRepo.findAllByCharity(charityId);
        if (comments.size() == 0) {
            throw new RowNotFoundException("There are no comments to the charity: " + charityId);
        }
        return comments;
    }

    @Override
    public List<Comment> getCommentsUser(Long userId) throws RowNotFoundException {
        List<Comment> comments = commentRepo.findAllByUserId(userId);
        if (comments.size() == 0) {
            throw new RowNotFoundException("There are no comments from the user: " + userId);
        }
        return comments;
    }
}
