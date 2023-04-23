package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.articleType.ArticleTypeDTO;
import com.example.kunuz_1.dto.profile.ProfileDTO;
import com.example.kunuz_1.entity.ArticleTypeEntity;
import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setNameUZ(dto.getNameUZ());
        entity.setNameRU(dto.getNameRU());
        entity.setNameEN(dto.getNameEN());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        articleTypeRepository.save(entity); // save profile

        dto.setId(entity.getId());
        return dto;
    }

    public void isValidProfile(ArticleTypeDTO dto) {
        // throw ...
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById((dto.getId()));
        if (optional.isPresent()) {
            throw new AppBadRequestException(" bu foydalanuvchi yoqku");
        }
    }

    public ArticleTypeDTO update(Integer id, ArticleTypeDTO dto) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(id);
        if(optional.isEmpty()){
            throw new AppBadRequestException("not found");
        }
        ArticleTypeEntity entity = optional.get();
        entity.setNameUZ(dto.getNameUZ());
        entity.setNameRU(dto.getNameRU());
        entity.setNameEN(dto.getNameEN());
        entity.setVisible(true);
        articleTypeRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public boolean delete(Integer id) {
        Optional<ArticleTypeEntity> byId = articleTypeRepository.findById(id);
        if (byId.isEmpty()){
            throw new AppBadRequestException("profile not found");
        }
        articleTypeRepository.delete(byId.get());
        return true;
    }

    public Page<ArticleTypeDTO> paginationGetAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ArticleTypeEntity> pageObj = articleTypeRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();
        List<ArticleTypeEntity> entityList = pageObj.getContent();
        List<ArticleTypeDTO> dtoList = new LinkedList<>();

        for (ArticleTypeEntity entity : entityList) {
            ArticleTypeDTO dto = new ArticleTypeDTO();
            dto.setId(entity.getId());
            dto.setNameUZ(entity.getNameUZ());
            dto.setNameRU(entity.getNameRU());
            dto.setNameEN(entity.getNameEN());
            dtoList.add(dto);
        }
        Page<ArticleTypeDTO> response = new PageImpl<ArticleTypeDTO>(dtoList, pageable, totalCount);
        return response;
    }

}
