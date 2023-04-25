package com.example.kunuz_1.repository;


import com.example.kunuz_1.entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer>,
        PagingAndSortingRepository<EmailHistoryEntity,Integer> {


}
