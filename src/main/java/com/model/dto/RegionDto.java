package com.model.dto;

import com.model.entity.Movie;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDto {
    private Integer id;
    private String regionName;
    private String regionSlug;
    private Set<Movie> movies;
}
