package com.begenerous.mapper;

import com.begenerous.DTO.CommentDTO;
import com.begenerous.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class CommentEntityMapper implements Mapper<CommentDTO, Comment> {
    @Override
    public Comment convertOne(CommentDTO requestCommentDTO) {
        return new Comment(
                requestCommentDTO.getComment(),
                requestCommentDTO.getIsAnonymous(),
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
        );
    }

    @Override
    public List<Comment> convertList(List<CommentDTO> requestCommentDTOs) {
        return requestCommentDTOs.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
