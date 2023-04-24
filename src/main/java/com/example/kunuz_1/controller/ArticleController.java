package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.article.ArticleDTO;
import com.example.kunuz_1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

  //  public ResponseEntity<ArticleDTO> create(@RequestBody  )
}
