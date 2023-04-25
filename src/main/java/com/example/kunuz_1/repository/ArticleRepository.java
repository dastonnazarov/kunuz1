package com.example.kunuz_1.repository;

import com.example.kunuz_1.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface ArticleRepository extends CrudRepository<ArticleEntity,Integer>,
        PagingAndSortingRepository<ArticleEntity,Integer> {
}
