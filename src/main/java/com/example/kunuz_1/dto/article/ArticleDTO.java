package com.example.kunuz_1.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
    private String title;
    private String description;
    private String content;
    private Integer shared_count;
    private Integer image_id;
    private Integer region_id;
    private Integer category_id;
    private Integer view_count;
    private Integer publisher_id;
    private Integer moderator_id;
}
