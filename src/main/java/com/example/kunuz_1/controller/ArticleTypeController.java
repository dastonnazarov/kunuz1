package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.articleType.ArticleTypeDTO;
import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.dto.profile.ProfileDTO;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.ArticleTypeService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping({"", "/"})
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto,
                                                 @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(articleTypeService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleTypeDTO> update(@PathVariable("id") Integer id, @RequestBody ArticleTypeDTO dto,
                                                 @RequestHeader("Authorization") String authorization){
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(articleTypeService.update(id,dto));
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
        return ResponseEntity.ok(articleTypeService.delete(id));
    }


    @PutMapping(value = "/pagingGetAll")
    public ResponseEntity<Page<ArticleTypeDTO>> paging(@RequestHeader("Authorization") String authorization,
                                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "size", defaultValue = "2") int size) {
        JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        Page<ArticleTypeDTO> response = articleTypeService.paginationGetAll(page, size);
        return ResponseEntity.ok(response);
    }

}
