package com.example.kunuz_1.controller;


import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.dto.profile.ProfileDTO;
import com.example.kunuz_1.entity.ProfileEntity;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.ProfileService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping({"", "/"})
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto,
                                             @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);

        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(profileService.create(dto, jwtDTO.getId()));
    }

    @PostMapping("/update1/{id}")
    public ResponseEntity<ProfileDTO> update1(@PathVariable("id")Integer id,@RequestBody ProfileDTO dto,
                                             @RequestHeader("Authorization")String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if(!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            throw new MethodNotAllowedException("Method not Allowed");
        }
        return ResponseEntity.ok(profileService.update1(id,dto));
    }



    @PutMapping("/update2/{id}")
    public ProfileDTO update2(@PathVariable("id")Integer id, @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.update2(id,dto)).getBody();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id")Integer id,
                                          @RequestHeader("Authorization")String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if(!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            throw new MethodNotAllowedException("Method not Allowed");
        }
        return ResponseEntity.ok(profileService.delete(id));
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") Integer id) {
//        return null;
//    }




    @GetMapping("/getAll")
    public ResponseEntity<Page<ProfileDTO>> pagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                          @RequestParam(value = "size",defaultValue = "6") int size,
                                                          @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(profileService.getAll(page,size));
    }
}
