package com.example.realsoft.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Board {
    private Long boardId;
    private String title;
    private Long userId;
    private Set<Long> memberIds;
    private LocalDate createdAt;
}
