package com.example.kunuz_1.repository;

import com.example.kunuz_1.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {
//    Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String em, String md5Hash, boolean b);
Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email,String password, boolean visible);
}
