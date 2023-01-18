package com.model.dto;

import com.model.entity.Movie;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    private String categoryName;
    private String categorySlug;
    private Set<Movie> movies;
}
