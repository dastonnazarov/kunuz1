package com.example.kunuz_1;

import com.example.kunuz_1.enums.ProfileRole;
import com.example.kunuz_1.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kunuz1Application {

    public static void main(String[] args) {
        SpringApplication.run(Kunuz1Application.class, args);
        System.out.println(JwtUtil.encode(7, ProfileRole.ADMIN));
    }

}

