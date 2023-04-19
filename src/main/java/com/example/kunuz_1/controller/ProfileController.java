package com.example.kunuz_1.controller;


import com.example.kunuz_1.dto.JwtDTO;
import com.example.kunuz_1.dto.ProfileDTO;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.ProfileService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/update1/{id}")
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

    @GetMapping("/getList")
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok(profileService.getAll());
    }




    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") Integer id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDTO> deleteById(@PathVariable("id") Integer id) {
        return null;
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<ProfileDTO>> pagination(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        return null;
    }
}