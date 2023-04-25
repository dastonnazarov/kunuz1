package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.article.ArticleDTO;
import com.example.kunuz_1.dto.articleType.ArticleTypeDTO;
import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.ArticleService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping({"","/"})
   public ResponseEntity<ArticleDTO> create(@RequestBody ArticleDTO dto,
                                            @RequestHeader("Authorization") String authorization){

       JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.MODERATOR);
       return ResponseEntity.ok(articleService.create(dto));
   }


    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleDTO> update(@PathVariable("id") Integer id, @RequestBody ArticleDTO dto,
                                                 @RequestHeader("Authorization") String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                             @RequestHeader("Authorization") String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<ArticleDTO> changeStatus(@PathVariable("id") Integer id,@RequestBody ArticleDTO dto,
                                             @RequestHeader("Authorization") String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(id,dto));
    }

}
