package com.example.kunuz_1.dto.article;


import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private Integer shared_count;
    private Integer image_id;
    private Integer region_id;
    private Integer category_id;
}
