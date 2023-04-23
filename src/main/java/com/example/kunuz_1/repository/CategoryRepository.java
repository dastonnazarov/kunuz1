package com.example.kunuz_1.repository;

import com.example.kunuz_1.entity.ArticleTypeEntity;
import com.example.kunuz_1.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository  extends CrudRepository<CategoryEntity,Integer>,
        PagingAndSortingRepository<CategoryEntity, Integer> {

}
