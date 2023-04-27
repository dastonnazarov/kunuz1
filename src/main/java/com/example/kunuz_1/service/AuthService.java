package com.example.kunuz_1.service;

import com.example.kunuz_1.dto.auth.AuthDTO;
import com.example.kunuz_1.dto.auth.AuthResponseDTO;
import com.example.kunuz_1.dto.auth.RegistrationDTO;
import com.example.kunuz_1.dto.auth.RegistrationResponseDTO;
import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.dto.profile.ProfileDTO;
import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.enums.GeneralStatus;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.AppBadRequestException;
import com.example.kunuz_1.excp.ItemNotFoundException;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.repository.ProfileRepository;
import com.example.kunuz_1.util.JwtUtil;
import com.example.kunuz_1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MailSenderService mailSenderService;

    public static JwtDTO getJwtDTO(String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        return JwtUtil.decode(jwt);
    }

    public static JwtDTO getJwtDTO(String authorization, ProfileRole... roleList) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        boolean roleFound = false;
        for (ProfileRole role : roleList) {
            if (jwtDTO.getRole().equals(role)) {
                roleFound = true;
                break;
            }
        }
        if (!roleFound) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return jwtDTO;
    }

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

    public ProfileDTO registration(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPasswordAndPhone(dto.getEmail(),MD5Util.getMd5Hash(dto.getPassword()),dto.getPhone());
        if(optional.isPresent()){
            throw new ItemNotFoundException("Email or phone or password incorrect");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setPhone(dto.getPhone());
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setRole(ProfileRole.ADMIN);
        profileRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

   /* public RegistrationResponseDTO registration(RegistrationDTO dto) {
        // TODO check -?
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new ItemNotFoundException("Email already exists mazgi.");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setRole(ProfileRole.USER);
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setStatus(GeneralStatus.REGISTER);
        // send email
        mailSenderService.sendRegistrationEmailMime(dto.getEmail());
        // save
        profileRepository.save(entity);
        String s = "Verification link was send to email: " + dto.getEmail();
        return new RegistrationResponseDTO(s);
    }*/

    public RegistrationResponseDTO emailVerification(String jwt) {
        String email = JwtUtil.decodeEmailVerification(jwt);
        Optional<ProfileEntity> optional = profileRepository.findByEmail(email);

        if (optional.isEmpty()) {
            throw new ItemNotFoundException("not found");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(GeneralStatus.REGISTER)) {
            throw new AppBadRequestException("wrong status!");
        }

        entity.setStatus(GeneralStatus.ACTIVE);
        profileRepository.save(entity);
        return new RegistrationResponseDTO("Registration done");
    }




}
