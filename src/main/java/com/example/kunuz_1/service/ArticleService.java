package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.article.ArticleDTO;
import com.example.kunuz_1.entity.*;
import com.example.kunuz_1.enums.ArticleStatus;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.excp.CategoryNotFoundException;
import com.example.kunuz_1.excp.ItemNotFoundException;
import com.example.kunuz_1.excp.RegionNotFoundException;
import com.example.kunuz_1.repository.ArticleRepository;
import com.example.kunuz_1.repository.CategoryRepository;
import com.example.kunuz_1.repository.ProfileRepository;
import com.example.kunuz_1.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private  ProfileRepository profileRepository;


    public ArticleDTO create(ArticleDTO dto) {
        isValidProfile(dto);
        Optional<RegionEntity> region = regionRepository.findById(dto.getRegion_id());
        Optional<CategoryEntity> category = categoryRepository.findById(dto.getCategory_id());
        Optional<ProfileEntity> moderator = profileRepository.findById(dto.getModerator_id());
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setShared_count(dto.getShared_count());
        entity.setImage_id(dto.getImage_id());
        entity.setRegion(region.get());
        entity.setCategory(category.get());
        entity.setStatus(ArticleStatus.NO_PUBLISHED);
        entity.setView_count(dto.getView_count());
        entity.setModerator(moderator.get());
        entity.setVisible(true);
        articleRepository.save(entity);
        return dto;
    }




    public void isValidProfile(ArticleDTO dto) {
        if(dto.getTitle()==null){
            throw new ItemNotFoundException("title not found");
        }if(dto.getDescription()==null){
            throw new ItemNotFoundException("description not found");
        }
        if(dto.getContent()==null){
            throw new ItemNotFoundException("content not found");
        }
        if(dto.getShared_count()==null){
            throw new ItemNotFoundException("shared_count not found");
        }
        if(dto.getImage_id()==null){
            throw new ItemNotFoundException("image_id not found");
        }
        if(dto.getRegion_id()==null){
            throw new ItemNotFoundException("region_id not found");
        }
        Optional<RegionEntity> regionOptional = regionRepository.findById(dto.getRegion_id());
        if (regionOptional.isEmpty()){
            throw new ItemNotFoundException("region not found");
        }
        if(dto.getCategory_id()==null){
            throw new ItemNotFoundException("category_id not found");
        }
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(dto.getCategory_id());
        if (categoryOptional.isEmpty()){
            throw new ItemNotFoundException("category not found");
        }
    }

    public ArticleDTO update(Integer id, ArticleDTO dto) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if(optional.isEmpty()){
            throw new AppBadRequestException("article not found");
        }

        Optional<RegionEntity> region = regionRepository.findById(dto.getRegion_id());
        Optional<CategoryEntity> category = categoryRepository.findById(dto.getCategory_id());
      //  Optional<ProfileEntity> publisher = profileRepository.findById(dto.getPublisher_id());
        Optional<ProfileEntity> moderator = profileRepository.findById(dto.getModerator_id());
        ArticleEntity entity = new ArticleEntity();
        entity.setId(id);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setShared_count(dto.getShared_count());
        entity.setImage_id(dto.getImage_id());
        entity.setRegion(region.get());
        entity.setCategory(category.get());
        entity.setStatus(ArticleStatus.NO_PUBLISHED);
        entity.setView_count(dto.getView_count());
      //  entity.setPublisher(publisher.get());
        entity.setModerator(moderator.get());
        entity.setVisible(true);
        articleRepository.save(entity);
        return dto;
    }
}
