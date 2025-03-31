package com.kevin.quizz.app.service;

import com.kevin.quizz.app.model.Question;
import com.kevin.quizz.app.repository.QuizzQuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuestionService {
    @Autowired
    private QuizzQuestionRepository quizzQuestionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = quizzQuestionRepository.findAll();

            if (questions.isEmpty()) {
                log.warn("No questions found in the database.");
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            log.error("Error fetching questions: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public boolean existsByQuestionText(String questionText) {
        return quizzQuestionRepository.existsByQuestionText(questionText);
    }

    public Question saveQuestion(Question question) {
        return quizzQuestionRepository.save(question);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> byCategory = quizzQuestionRepository.findByCategoryEqualsIgnoreCase(category);

        if (byCategory.isEmpty()) {
            log.error("No questions found for category: {}", category);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byCategory);
    }
}
