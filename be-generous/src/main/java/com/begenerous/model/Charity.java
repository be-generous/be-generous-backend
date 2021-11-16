package com.begenerous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "date_created",
            nullable = false
    )
    private Long dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "charity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "charity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Donation> donation = new ArrayList<>();

    public Charity(Long charityId, Double goalAmount, Double currentAmount, String coverImageURL, String title, String description, Long dateCreated, User user) {
        this.charityId = charityId;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.coverImageURL = coverImageURL;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.user = user;
    }
}
