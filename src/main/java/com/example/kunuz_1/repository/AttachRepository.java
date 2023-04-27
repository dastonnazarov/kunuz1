package com.example.kunuz_1.repository;


import com.example.kunuz_1.entity.AttachEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AttachRepository extends CrudRepository<AttachEntity,Integer>, PagingAndSortingRepository<AttachEntity,Integer> {

    Optional<Object> findById(String id);
}
