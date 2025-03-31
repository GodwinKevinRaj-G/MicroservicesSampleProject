package com.kevin.quizz.app.controller;

import com.kevin.quizz.app.model.Question;
import com.kevin.quizz.app.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/addQuestions")
    public ResponseEntity<String> createQuestion(@RequestBody Question question) {
        boolean exists = questionService.existsByQuestionText(question.getQuestionText());
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Question already exists!");
        }
        questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body("Question created successfully!");
    }

    @GetMapping("/getQuestionByCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

}
