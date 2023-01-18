package com.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "regions")
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String regionName;
    private String regionSlug;
    @ManyToMany(mappedBy = "regions")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Movie> movies;
}
