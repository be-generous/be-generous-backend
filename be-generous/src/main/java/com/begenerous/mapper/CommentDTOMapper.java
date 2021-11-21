package com.begenerous.mapper;

import com.begenerous.DTO.CommentDTO;
import com.begenerous.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDTOMapper implements Mapper<Comment, CommentDTO> {
    @Override
    public CommentDTO convertOne(Comment comment) {
        return new CommentDTO(
                comment.getComment(),
                comment.getIsAnonymous(),
                comment.getDateCreated(),
                comment.getUser().getUserId(),
                comment.getCharity().getCharityId()
        );
    }

    @Override
    public List<CommentDTO> convertList(List<Comment> comments) {
        return comments.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
