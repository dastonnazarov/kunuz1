package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.category.CategoryDTO;
import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.CategoryService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping({"", "/"})
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto,
                                              @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);

        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(categoryService.create(dto, jwtDTO.getId()));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id, @RequestBody CategoryDTO dto,
                                            @RequestHeader("Authorization") String authorization){
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(categoryService.update(id,dto));
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
        return ResponseEntity.ok(categoryService.delete(id));
    }


    @PutMapping(  "/getAll")
    public ResponseEntity<Page<CategoryDTO>> paging(@RequestHeader("Authorization") String authorization,
                                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size) {
        JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        Page<CategoryDTO> response = categoryService.paginationGetAll(page, size);
        return ResponseEntity.ok(response);
    }
}
