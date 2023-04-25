package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.dto.region.RegionDTO;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.RegionService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping({"", "/"})
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                            @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);

        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(regionService.create(dto));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<RegionDTO> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto,
                                                  @RequestHeader("Authorization") String authorization){
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(regionService.update(id,dto));
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
        return ResponseEntity.ok(regionService.delete(id));
    }


    @PutMapping(value = "/pagingGetAll")
    public ResponseEntity<Page<RegionDTO>> paging(@RequestHeader("Authorization") String authorization,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "2") int size) {
        JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        Page<RegionDTO> response = regionService.paginationGetAll(page, size);
        return ResponseEntity.ok(response);
    }

}
