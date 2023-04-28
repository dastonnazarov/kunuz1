package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.article.ArticleDTO;
import com.example.kunuz_1.dto.article.ArticleRequestDTO;
import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.ArticleService;
import com.example.kunuz_1.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<ArticleRequestDTO> create(@RequestBody  @Valid ArticleRequestDTO dto,
                                                    @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, jwt.getId()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleRequestDTO> update(@PathVariable("id") String id, @RequestBody ArticleRequestDTO dto,
                                                 @RequestHeader("Authorization") String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(dto,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                             @RequestHeader("Authorization") String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") Integer id,@RequestBody ArticleDTO dto,
                                             @RequestHeader("Authorization") String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(id,dto));
    }

    @GetMapping("/getListLast5")
    public ResponseEntity<Page<ArticleRequestDTO>> getListLast5(@RequestParam(value = "page",defaultValue = "1") int page,
                                                       @RequestParam(value = "size",defaultValue = "6") int size,
                                                       @RequestHeader("Authorization") String authorization) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.getListLast5(page,size));
    }

}
