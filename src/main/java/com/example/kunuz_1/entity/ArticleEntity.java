package com.example.kunuz_1.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "shared_count")
    private Integer shared_count;

    @Column(name = "image_Id")
    private Integer image_id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private  Integer region_id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Integer category_id;
}
