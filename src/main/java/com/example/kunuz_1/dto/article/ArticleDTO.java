package com.example.kunuz_1.dto.article;

import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.entity.RegionEntity;
import com.example.kunuz_1.enums.ArticleStatus;
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
    private ArticleStatus status;
    private Integer image_id;
    private Integer region_id;
    private Integer category_id;
    private Integer view_count;
    private ProfileEntity publisher;
    private ProfileEntity moderator;
    private Boolean visible;
}
