package com.example.kunuz_1.dto;

import com.example.kunuz_1.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class AuthResponseDTO {
    private  String name;
    private String surname;
    private ProfileRole role;
    private String jwt;

}
