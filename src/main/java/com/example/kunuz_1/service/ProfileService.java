package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.profile.ProfileDTO;
import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.enums.GeneralStatus;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.repository.ProfileRepository;
import com.example.kunuz_1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    public Page<ProfileDTO> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1,size,sort);

        Page<ProfileEntity> pageObj = profileRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();

        List<ProfileEntity> courseEntitieList = pageObj.getContent();
        List<ProfileDTO> list = new LinkedList<>();

        for(ProfileEntity entity : courseEntitieList){
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setEmail(entity.getEmail());
            dto.setRole(entity.getRole());
            dto.setPassword(entity.getPassword()); // MD5 ?
            //dto.setVisible(true);
            dto.setStatus(GeneralStatus.ACTIVE);

            list.add(dto);
        }
        Page<ProfileDTO> response = new PageImpl<>(list,pageable,totalCount);

        return response;
    }

  /*  public Page<ProfileDTO> paginationGetAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ProfileEntity> pageObj = profileRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();
        List<ProfileEntity> entityList = pageObj.getContent();
        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            ProfileDTO dto = new ProfileDTO();

            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPhone(entity.getPhone());
            dto.setPassword(entity.getPassword());
            dto.setRole(entity.getRole());
            dto.setSurname(entity.getSurname());
            dto.setStatus(entity.getStatus());
            dto.setEmail(entity.getEmail());
            dto.setVisible(entity.getVisible());
            dtoList.add(dto);
        }
        Page<ProfileDTO> response = new PageImpl<ProfileDTO>(dtoList, pageable, totalCount);
        return response;
    }*/

    public boolean delete(Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if (byId.isEmpty()){
            throw new AppBadRequestException("profile not found");
        }
        profileRepository.delete(byId.get());
        return true;
    }
}
