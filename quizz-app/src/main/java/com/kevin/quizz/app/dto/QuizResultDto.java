package com.kevin.quizz.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDto {
    private Long quizId;
    private int totalQuestions;
    private int correctAnswers;
}

