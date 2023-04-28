package com.example.kunuz_1.entity;


import com.example.kunuz_1.enums.ArticleStatus;
import com.example.kunuz_1.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Getter
//@Setter
//@Entity
//@Table(name = "article")
//public class ArticleEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "title",columnDefinition = "text")
//    private String title;
//
//    @Column(name = "description",columnDefinition = "text")
//    private String description;
//
//    @Column(name = "content",columnDefinition = "text")
//    private String content;
//
//    @Column(name = "shared_count")
//    private Integer shared_count;
//
//    @Column(name = "image_Id")
//    private Integer image_id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "region_id")
//    private RegionEntity region;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private CategoryEntity category;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "moderator_id")
//    private ProfileEntity moderator;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "publisher_id")
//    private ProfileEntity publisher;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private ArticleStatus status;
//
//    @Column(name = "created_date")
//    private LocalDateTime created_date = LocalDateTime.now();
//
//    @Column(name = "published_date")
//    private LocalDateTime published_date = LocalDateTime.now();
//
//    @Column(name = "visible")
//    private Boolean visible;
//
//    @Column(name = "view_count")
//    private Integer view_count;


@Getter
@Setter
@Table(name = "article")
@Entity
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ArticleStatus status = ArticleStatus.NO_PUBLISHED;

    @Column(name = "shared_count")
    private Integer sharedCount = 0;

    @Column(name = "attach_id")
    private Integer attachId;

    @ManyToOne
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "region_id")
    private Integer regionId;

    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private RegionEntity region;

    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "moderator_id")
    private Integer moderatorId;

    @ManyToOne
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    private ProfileEntity moderator;

    @Column(name = "publisher_id")
    private Integer publisherId;

    @ManyToOne
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    private ProfileEntity publisher;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "view_count")
    private Integer viewCount;



}
