package com.example.kunuz_1.repository;



import com.example.kunuz_1.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<RegionEntity,Integer>, PagingAndSortingRepository<RegionEntity,Integer> {
}
