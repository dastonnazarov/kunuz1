package com.example.kunuz_1.controller;

import com.example.kunuz_1.dto.auth.AuthDTO;
import com.example.kunuz_1.dto.auth.AuthResponseDTO;
import com.example.kunuz_1.dto.auth.RegistrationDTO;
import com.example.kunuz_1.dto.auth.RegistrationResponseDTO;
import com.example.kunuz_1.dto.profile.ProfileDTO;
import com.example.kunuz_1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthDTO dto) {
        //return ResponseEntity.ok(authService.login(dto));
        return null;
    }

   @PostMapping({"/registration"})
   public  ResponseEntity<?>registration(@RequestBody RegistrationDTO dto){
        return ResponseEntity.ok(authService.registration(dto));
    }

    @GetMapping("/email/verification/{jwt}")
    public ResponseEntity<RegistrationResponseDTO> emailVerification(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }
}
