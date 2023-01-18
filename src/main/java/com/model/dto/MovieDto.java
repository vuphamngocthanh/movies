package com.model.dto;

import com.model.entity.Category;
import com.model.entity.Episode;
import com.model.entity.Region;
import com.model.enum_store.Status;
import com.model.enum_store.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Integer id;
    private String movieName;
    private String originName;
    private String movieSlug;
    private String movieThumb;
    private String moviePoster;
    private String content;
    private Type type;
    private Status status;
    private String episodeTime;
    private String totalEpisode;
    private String episodeCurrent;
    private String movieQuality;
    private String movieLanguage;
    private String publishYear;
    private Timestamp createdAt;
    private Set<Category> categories;
    private Set<Region> regions;
    private List<Episode> episodes;
}
