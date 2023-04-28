package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.article.ArticleDTO;
import com.example.kunuz_1.dto.article.ArticleRequestDTO;
import com.example.kunuz_1.dto.profile.ProfileDTO;
import com.example.kunuz_1.entity.*;
import com.example.kunuz_1.enums.ArticleStatus;
import com.example.kunuz_1.enums.GeneralStatus;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.excp.ItemNotFoundException;
import com.example.kunuz_1.repository.ArticleRepository;
import com.example.kunuz_1.repository.CategoryRepository;
import com.example.kunuz_1.repository.ProfileRepository;
import com.example.kunuz_1.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ProfileService profileService;
    private final RegionService regionService;
    private final CategoryService categoryService;
    private final ArticleRepository articleRepository;

    private ProfileRepository profileRepository;
    private RegionRepository regionRepository;
    private CategoryRepository categoryRepository;


    public ArticleRequestDTO create(ArticleRequestDTO dto, Integer moderId) {
        // check
//        ProfileEntity moderator = profileService.get(moderId);
//        RegionEntity region = regionService.get(dto.getRegionId());
//        CategoryEntity category = categoryService.get(dto.getCategoryId());

        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(moderId);
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        // type
        articleRepository.save(entity);
        return dto;
    }


    public void isValidProfile(ArticleRequestDTO dto) {
        if (dto.getTitle() == null) {
            throw new ItemNotFoundException("title not found");
        }
        if (dto.getDescription() == null) {
            throw new ItemNotFoundException("description not found");
        }
        if (dto.getContent() == null) {
            throw new ItemNotFoundException("content not found");
        }
        if (dto.getTypeList() == null) {
            throw new ItemNotFoundException("getTypeList not found");
        }
        if (dto.getAttachId() == null) {
            throw new ItemNotFoundException("attach not found");
        }
        if (dto.getRegionId() == null) {
            throw new ItemNotFoundException("region_id not found");
        }
        if (dto.getCategoryId() == null) {
            throw new ItemNotFoundException("category_id not found");
        }

    }

    public ArticleRequestDTO update(ArticleRequestDTO dto, String id) {
        ArticleEntity entity = get(id);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setStatus(ArticleStatus.NO_PUBLISHED);
        articleRepository.save(entity);
        return dto;
    }

    public Boolean delete(Integer id) {
        Optional<ArticleEntity> articleId = articleRepository.findById(id);
        if (articleId.isEmpty()) {
            throw new ItemNotFoundException("item not found");
        }
        articleRepository.delete(articleId.get());
        return true;
    }

    public Boolean changeStatus(Integer id, ArticleDTO dto) {

        Optional<ArticleEntity> articleEntity = articleRepository.findById(id);
        if (articleEntity.isEmpty()) {
            throw new AppBadRequestException("article not found");
        }
        ArticleEntity entity = articleEntity.get();
        //TODO UPDATE status
        if (!dto.getStatus().equals(ArticleStatus.NO_PUBLISHED)) {
            entity.setStatus(ArticleStatus.PUBLISHED);
            articleRepository.save(entity);

        } else if (!dto.getStatus().equals(ArticleStatus.PUBLISHED)) {
            entity.setStatus(ArticleStatus.NO_PUBLISHED);
            entity.setVisible(true);
            articleRepository.save(entity);

        }
        return true;

    }


    public Page<ArticleRequestDTO> getListLast5(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<ArticleEntity> pageObj = articleRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();

        List<ArticleEntity> articleEntityList = pageObj.getContent();
        List<ArticleRequestDTO> list = new LinkedList<>();

        for (ArticleEntity entity : articleEntityList) {
            ArticleRequestDTO dto = new ArticleRequestDTO();
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setContent(entity.getContent());
            dto.setAttachId(entity.getAttachId());
            dto.setRegionId(entity.getRegionId());
            dto.setCategoryId(entity.getCategoryId());
            list.add(dto);
        }
        Page<ArticleRequestDTO> response = new PageImpl<>(list, pageable, totalCount);
        return response;
    }


    public ArticleEntity get(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(Integer.valueOf(id));
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }
}
