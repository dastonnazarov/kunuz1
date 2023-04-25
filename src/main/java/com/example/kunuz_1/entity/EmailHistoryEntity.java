package com.example.kunuz_1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "email_history")
public class EmailHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "message")
    private String message;
    @Column(name = "email")
    private String email;
    @Column(name = "create_date")
    private LocalDateTime created_data;

}
