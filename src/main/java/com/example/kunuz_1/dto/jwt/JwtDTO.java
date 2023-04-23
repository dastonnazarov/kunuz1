package com.example.kunuz_1.dto.jwt;

import com.example.kunuz_1.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    private  Integer id;
    private ProfileRole role;


}
