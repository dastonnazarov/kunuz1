package com.example.kunuz_1.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleTypeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name_uz")
    private String nameUZ;

    @Column(name = "name_en")
    private String nameEN;

    @Column(name = "name_ru")
    private String nameRU;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();

    @Column(name = "key")
    private String key;

}
