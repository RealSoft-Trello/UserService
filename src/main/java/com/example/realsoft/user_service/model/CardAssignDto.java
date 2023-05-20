package com.example.realsoft.user_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardAssignDto {
    private Long cardId;
    private Long userId;
    private String title;
}
