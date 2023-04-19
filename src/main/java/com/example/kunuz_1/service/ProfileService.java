package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.ProfileDTO;
import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.enums.GeneralStatus;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.repository.ProfileRepository;
import com.example.kunuz_1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO create(ProfileDTO dto, Integer adminId) {
        // check - homework
        isValidProfile(dto);

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword())); // MD5 ?
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setPrtId(adminId);
        profileRepository.save(entity); // save profile

        dto.setPassword(null);
        dto.setId(entity.getId());
        return dto;
    }

    public void isValidProfile(ProfileDTO dto) {

        // throw ...
        Optional<ProfileEntity> byId = profileRepository.findById(dto.getId());
        if(byId.isEmpty()){
            throw new AppBadRequestException("profile not found");
        }

    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new AppBadRequestException("Profile not found");
        });
    }

    public ProfileDTO update1(Integer id, ProfileDTO dto) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if(byId.isEmpty()){
            throw new AppBadRequestException("bad exception");
        }
        ProfileEntity entity = byId.get();
        if (!entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("this profile cann't update");
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setRole(dto.getRole());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public ProfileDTO update2(Integer id, ProfileDTO dto) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if(byId.isEmpty()){
            throw new AppBadRequestException("bad exception");
        }
        ProfileEntity entity = byId.get();
        if (!entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("this profile cann't update");
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setRole(dto.getRole());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<ProfileDTO> getAll() {
        Iterable<ProfileEntity> all = profileRepository.findAll();
        List<ProfileDTO> profileDTOS = new LinkedList<>();
        all.forEach(entity -> {
           ProfileDTO dto = new ProfileDTO();
           dto.setId(entity.getId());
           dto.setName(entity.getName());
           dto.setSurname(entity.getSurname());
           dto.setEmail(entity.getEmail());
           dto.setPhone(entity.getPhone());
           dto.setPassword(MD5Util.getMd5Hash(entity.getPassword()));
           dto.setRole(entity.getRole());
           dto.setStatus(GeneralStatus.ACTIVE);
           profileDTOS.add(dto);
        });
        return profileDTOS;
    }
}
