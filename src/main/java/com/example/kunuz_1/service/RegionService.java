package com.example.kunuz_1.service;


import com.example.kunuz_1.dto.region.RegionDTO;
import com.example.kunuz_1.entity.RegionEntity;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;


    public RegionDTO create(RegionDTO dto) {
        isValidProfile(dto);
        RegionEntity entity = new RegionEntity();
        entity.setNameUz(dto.getNameUZ());
        entity.setNameRu(dto.getNameRU());
        entity.setNameEng(dto.getNameEN());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        regionRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public RegionDTO update(Integer id, RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("not found");
        }
        RegionEntity entity = optional.get();
        entity.setNameUz(dto.getNameUZ());
        entity.setNameRu(dto.getNameRU());
        entity.setNameEng(dto.getNameEN());
        entity.setVisible(true);
        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean delete(Integer id) {
        Optional<RegionEntity> byId = regionRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadRequestException("profile not found");
        }
        regionRepository.delete(byId.get());
        return true;
    }

    public Page<RegionDTO> paginationGetAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<RegionEntity> pageObj = regionRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();
        List<RegionEntity> entityList = pageObj.getContent();

        List<RegionDTO> dtoList = new LinkedList<>();

        for (RegionEntity entity : entityList) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setNameUZ(entity.getNameUz());
            dto.setNameRU(entity.getNameRu());
            dto.setNameEN(entity.getNameEng());
            dtoList.add(dto);
        }
        Page<RegionDTO> response = new PageImpl<RegionDTO>(dtoList, pageable, totalCount);
        return response;
    }

    public void isValidProfile(RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById((dto.getId()));
        if (optional.isPresent()) {
            throw new AppBadRequestException(" bu foydalanuvchi yoqku");
        }
    }

    public RegionEntity get(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("region not found");
        }
        return optional.get();
    }
}
