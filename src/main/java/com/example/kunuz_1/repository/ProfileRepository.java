package com.example.kunuz_1.repository;

import com.example.kunuz_1.entity.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer>, PagingAndSortingRepository<ProfileEntity,Integer> {

    Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email,String password, boolean visible);

    Optional<ProfileEntity> findByEmailAndPasswordAndPhone(String email, String md5Hash, String phone);

    Optional<ProfileEntity> findByEmail(String email);
}
