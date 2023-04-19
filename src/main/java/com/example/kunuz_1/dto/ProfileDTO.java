package com.example.kunuz_1.dto;


import com.example.kunuz_1.enums.GeneralStatus;
import com.example.kunuz_1.enums.ProfileRole;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileRole role;
    private GeneralStatus status;


}
