package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.articleType.ArticleTypeDTO;
import com.example.kunuz_1.dto.category.CategoryDTO;
import com.example.kunuz_1.dto.region.RegionDTO;
import com.example.kunuz_1.entity.ArticleEntity;
import com.example.kunuz_1.entity.ArticleTypeEntity;
import com.example.kunuz_1.entity.CategoryEntity;
import com.example.kunuz_1.entity.RegionEntity;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.repository.CategoryRepository;
import com.example.kunuz_1.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto, Integer id) {

        CategoryEntity entity = new CategoryEntity();
        entity.setNameEng(dto.getNameEN());
        entity.setNameRu(dto.getNameRU());
        entity.setNameUz(dto.getNameUZ());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        categoryRepository.save(entity); // save profile

        dto.setId(entity.getId());
        return dto;
    }

    public void isValidProfile(ArticleTypeDTO dto) {
        // throw ...
        Optional<CategoryEntity> optional = categoryRepository.findById((dto.getId()));
        if (optional.isPresent()) {
            throw new AppBadRequestException(" bu foydalanuvchi yoqku");
        }
    }

    public CategoryDTO update(Integer id, CategoryDTO dto) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("not found");
        }
        CategoryEntity entity = optional.get();
        entity.setNameUz(dto.getNameUZ());
        entity.setNameRu(dto.getNameRU());
        entity.setNameEng(dto.getNameEN());
        entity.setVisible(true);
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean delete(Integer id) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadRequestException("profile not found");
        }
        categoryRepository.delete(byId.get());
        return true;
    }

    public Page<CategoryDTO> paginationGetAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<CategoryEntity> pageObj = categoryRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();
        List<CategoryEntity> entityList = pageObj.getContent();

        List<CategoryDTO> dtoList = new LinkedList<>();

        for (CategoryEntity entity : entityList) {

            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setNameUZ(entity.getNameUz());
            dto.setNameRU(entity.getNameRu());
            dto.setNameEN(entity.getNameEng());

            dtoList.add(dto);
        }
        Page<CategoryDTO> response = new PageImpl<CategoryDTO>(dtoList, pageable, totalCount);
        return response;
    }

    public CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new AppBadRequestException("category not found");
        });
    }
}
