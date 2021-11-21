package com.begenerous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "Comment"
)
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    @Column(
            name = "comment_id"
    )
    private Long commentId;

    @Column(
            name = "comment"
    )
    private String comment;

    @Column(
            name = "is_anonymous"
    )
    private Boolean isAnonymous;

    @Column(
            name = "date"
    )
    private Long dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "charity_id", nullable = false)
    private Charity charity;

    public Comment(String comment, Boolean isAnonymous, Long dateCreated) {
        this.comment = comment;
        this.isAnonymous = isAnonymous;
        this.dateCreated = dateCreated;
    }
}
