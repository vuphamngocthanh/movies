package com.model.entity;

import com.model.enum_store.Type;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Data
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movieName;
    private String originName;
    private String movieSlug;
    private String movieThumb;
    private String moviePoster;
    private String content;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private com.model.enum_store.Status Status;
    private String episodeTime;
    private String totalEpisode;
    private String episodeCurrent;
    private String movieQuality;
    private String movieLanguage;
    private String publishYear;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_categories",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Category> categories;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_regions",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "regions_id" ))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Region> regions;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "movies_episodes",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "episodes_id" ))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Episode> episodes = new ArrayList<>();

    public List<Episode> getEpisodes() {
        return this.episodes;
    }
}
