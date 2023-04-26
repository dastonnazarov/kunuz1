package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.article.ArticleDTO;
import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.excp.MethodNotAllowedException;
import com.example.kunuz_1.service.ArticleService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") Integer id,@RequestBody ArticleDTO dto,
                                             @RequestHeader("Authorization") String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization,ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(id,dto));
    }

    @GetMapping("/getListLast5")
    public ResponseEntity<Page<ArticleDTO>> getListLast5(@RequestParam(value = "page",defaultValue = "1") int page,
                                                       @RequestParam(value = "size",defaultValue = "6") int size,
                                                       @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.MODERATOR)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(articleService.getListLast5(page,size));
    }

}
