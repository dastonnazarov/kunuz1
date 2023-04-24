package com.example.kunuz_1.repository;

import com.example.kunuz_1.entity.ArticleTypeEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity,Integer> , PagingAndSortingRepository<ArticleTypeEntity, Integer> {

}
