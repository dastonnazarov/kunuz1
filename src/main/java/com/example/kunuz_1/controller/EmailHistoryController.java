package com.example.kunuz_1.controller;


import com.example.kunuz_1.dto.article.ArticleDTO;
import com.example.kunuz_1.dto.emailHistory.EmailHistoryDTO;
import com.example.kunuz_1.dto.jwt.JwtDTO;
import com.example.kunuz_1.entity.EmailHistoryEntity;
import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.service.EmailHistoryService;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email_history")
public class EmailHistoryController {
    @Autowired
    private EmailHistoryService emailHistoryService;

    @PostMapping({"","/"})
    public ResponseEntity<ArticleDTO> create(@RequestBody ArticleDTO dto,
                                             @RequestHeader("Authorization") String authorization){

        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
       // return ResponseEntity.ok(emailHistoryService.create(dto));
        return  null;
    }
}
