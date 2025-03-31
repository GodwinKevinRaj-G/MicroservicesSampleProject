package com.kevin.quizz.app.service;

import com.kevin.quizz.app.dto.*;
import com.kevin.quizz.app.model.Option;
import com.kevin.quizz.app.model.Question;
import com.kevin.quizz.app.model.Quiz;
import com.kevin.quizz.app.repository.OptionRepository;
import com.kevin.quizz.app.repository.QuizRepository;
import com.kevin.quizz.app.repository.QuizzQuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    OptionRepository optionRepository;


    @Autowired
    QuizzQuestionRepository quizzQuestionRepository;


    public ResponseEntity<String> createQuiz(String category, int numberOfQuestion, String title) {
        List<Question> questions = quizzQuestionRepository.findRandomQuestionsByCategory(category, numberOfQuestion);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public QuizResponseDto getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<QuestionResponseDto> questionResponses = quiz.getQuestions().stream().map(question -> {
            List<Option> options = question.getOptions();
            return new QuestionResponseDto(question.getId(), question.getQuestionText(), options.size() > 0 ? options.get(0).getOptionText() : null, options.size() > 1 ? options.get(1).getOptionText() : null, options.size() > 2 ? options.get(2).getOptionText() : null, options.size() > 3 ? options.get(3).getOptionText() : null);
        }).toList();

        return new QuizResponseDto(quiz.getId(), quiz.getTitle(), questionResponses);
    }

    public QuizResultDto validateQuiz(QuizSubmissionDto submissionDto) {
        Quiz quiz = quizRepository.findById(submissionDto.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        int correctAnswers = 0;
        int totalQuestions = submissionDto.getAnswers().size();

        for (UserAnswerDto answerDto : submissionDto.getAnswers()) {
            // Check if the selected option is correct
            boolean isCorrect = optionRepository.existsByIdAndIsCorrectTrue(answerDto.getSelectedOptionId());
            if (isCorrect) {
                correctAnswers++;
            }
        }

        return new QuizResultDto(quiz.getId(), totalQuestions, correctAnswers);
    }
}
