package com.kevin.quizz.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDto {
    private Long questionId;
    private Long selectedOptionId;
}

