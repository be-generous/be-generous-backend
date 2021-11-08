package com.begenerous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "role",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "role_name_unique", columnNames = "role_name"
        )
}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(
            name = "roles_sequence",
            sequenceName = "roles_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "roles_sequence"
    )
    @Column(
            name = "role_id"
    )
    private Long roleId;

    @Column(
            name = "role_name"
    )
    private String name;

}
