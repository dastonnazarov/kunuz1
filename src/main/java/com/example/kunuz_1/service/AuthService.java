package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.AuthDTO;
import com.example.kunuz_1.dto.AuthResponseDTO;
import com.example.kunuz_1.dto.ProfileDTO;
import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.enums.GeneralStatus;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.excp.ItemNotFoundException;
import com.example.kunuz_1.repository.ProfileRepository;
import com.example.kunuz_1.util.JwtUtil;
import com.example.kunuz_1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public AuthResponseDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPasswordAndVisible(
                dto.getEmail(),
                MD5Util.getMd5Hash(dto.getPassword()),
                true);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Email or password incorrect");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("Wrong status");
        }
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        responseDTO.setJwt(JwtUtil.encode(entity.getId(), entity.getRole()));
        return responseDTO;
    }

    public Object registration(ProfileDTO dto) {
        Optional<ProfileEntity> entity = profileRepository.findByEmailAndPasswordAndPhone(dto.getEmail(),
                MD5Util.getMd5Hash(dto.getPassword()),
                dto.getPhone());
        if(entity.isPresent()){
            throw new ItemNotFoundException("Email and Password and Phone are incorrect");
        }
        ProfileEntity entities = new ProfileEntity();
        entities.setName(dto.getName());
        entities.setSurname(dto.getSurname());
        entities.setEmail(dto.getEmail());
        entities.setPhone(dto.getPhone());
        entities.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entities.setStatus(GeneralStatus.ACTIVE);
        entities.setRole(ProfileRole.USER);
        profileRepository.save(entities);
        dto.setId(entities.getId());
        return dto;
    }
}
