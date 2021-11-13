package com.begenerous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(
        name = "charity"
)
@AllArgsConstructor
@NoArgsConstructor
public class Charity {

    @Id
    @SequenceGenerator(
            name = "charity_sequence",
            sequenceName = "charity_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "charity_sequence"
    )
    @Column(
            name = "charity_id",
            unique = true
    )
    private Long charityId;

    @Column(
            name = "goal_amount",
            nullable = false
    )
    private Double goalAmount;

    @Column(
            name = "current_amount"
    )
    private Double currentAmount;

    @Column(
            name = "cover_image_url"
    )
    private String coverImageURL;

    @Column(
            name = "title"
    )
    private String title;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "date_created"
    )
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
