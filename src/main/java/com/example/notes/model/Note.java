package com.example.notes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String heading;
    @Column(nullable = false)
    private String content;
    @Column
    private LocalDateTime local_date_time = LocalDateTime.now();
}
