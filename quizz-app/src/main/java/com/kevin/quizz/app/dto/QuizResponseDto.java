package com.kevin.quizz.app.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponseDto {
    private Long id;
    private String title;
    private List<QuestionResponseDto> questions;
}

