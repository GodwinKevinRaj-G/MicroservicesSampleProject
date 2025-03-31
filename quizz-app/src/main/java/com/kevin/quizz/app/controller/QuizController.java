package com.kevin.quizz.app.controller;

import com.kevin.quizz.app.dto.QuizResponseDto;
import com.kevin.quizz.app.dto.QuizResultDto;
import com.kevin.quizz.app.dto.QuizSubmissionDto;
import com.kevin.quizz.app.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numberOfQuestion, @RequestParam String title) {
        return quizService.createQuiz(category, numberOfQuestion, title);
    }

    @GetMapping("/{quizId}")
    public QuizResponseDto getQuiz(@PathVariable Long quizId) {
        return quizService.getQuizById(quizId);
    }

    @PostMapping("/validate")
    public ResponseEntity<QuizResultDto> validateQuiz(@RequestBody QuizSubmissionDto submissionDto) {
        QuizResultDto result = quizService.validateQuiz(submissionDto);
        return ResponseEntity.ok(result);
    }
}