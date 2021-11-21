package com.begenerous.controller;

import com.begenerous.DTO.CommentDTO;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.CommentDTOMapper;
import com.begenerous.mapper.CommentEntityMapper;
import com.begenerous.model.Comment;
import com.begenerous.service.CommentService;
import com.begenerous.util.ExceptionHandlerUtils;
import com.begenerous.util.ResponseBodyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentEntityMapper commentEntityMapper;
    private final CommentDTOMapper commentDTOMapper;

    @PostMapping
    public ResponseEntity<?> saveComment(@Valid @RequestBody CommentDTO commentDTO) {
        try {
            Comment comment = commentService.saveComment(
                    commentEntityMapper.convertOne(commentDTO),
                    commentDTO.getUserId(),
                    commentDTO.getCharityId()
            );
            ResponseBodyUtil responseBodyUtil = new ResponseBodyUtil();
            responseBodyUtil.addToResponseBody("message", "Comment successfully created!");
            responseBodyUtil.addToResponseBody("commentId", comment.getCommentId().toString());

            return new ResponseEntity<>(responseBodyUtil.createResponseBody(), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<?> getCommentByUser(@PathVariable("userId") String userId) {
        try {
            Long longUserId = Long.valueOf(userId);
            return new ResponseEntity<>(commentDTOMapper.convertList(commentService.getCommentsUser(longUserId)), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        } catch (Exception e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }

    @GetMapping(path = "/charity/{charityId}")
    public ResponseEntity<?> getCommentByCharity(@PathVariable("charityId") String charityId) {
        try {
            Long longCharityId = Long.valueOf(charityId);
            return new ResponseEntity<>(commentDTOMapper.convertList(commentService.getCommentsCharity(longCharityId)), HttpStatus.OK);
        } catch (RowNotFoundException e) {
            return ExceptionHandlerUtils.rowNotFoundException(e.getMessage());
        } catch (Exception e) {
            return ExceptionHandlerUtils.unexpectedException(e.getMessage());
        }
    }
}
