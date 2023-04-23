package com.example.kunuz_1.dto.profile;


import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.enums.GeneralStatus;
import com.example.kunuz_1.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO  {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Boolean visible;
    private String password;
    private ProfileRole role;
    private GeneralStatus status;


}
