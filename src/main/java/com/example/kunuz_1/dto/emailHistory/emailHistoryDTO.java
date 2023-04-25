package com.example.kunuz_1.dto.emailHistory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class emailHistoryDTO {
   private Integer id;
   private String message;
   private String email;
   private LocalDateTime created_data;
}
