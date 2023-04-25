package com.example.kunuz_1.entity;


import com.example.kunuz_1.enums.ArticleStatus;
import com.example.kunuz_1.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title",columnDefinition = "text")
    private String title;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "content",columnDefinition = "text")
    private String content;

    @Column(name = "shared_count")
    private Integer shared_count;

    @Column(name = "image_Id")
    private Integer image_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    private ProfileEntity moderator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private ProfileEntity publisher;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ArticleStatus status;

    @Column(name = "created_date")
    private LocalDateTime created_date = LocalDateTime.now();

    @Column(name = "published_date")
    private LocalDateTime published_date = LocalDateTime.now();

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "view_count")
    private Integer view_count;



}
