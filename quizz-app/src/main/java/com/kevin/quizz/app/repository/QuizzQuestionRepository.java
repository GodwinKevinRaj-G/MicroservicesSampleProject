package com.kevin.quizz.app.repository;

import com.kevin.quizz.app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzQuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByQuestionText(String questionText);

    List<Question> findByCategoryEqualsIgnoreCase(String category);

    @Query(value = "SELECT * FROM questions q WHERE q.category = ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numberOfQuestion") int numberOfQuestion);

}
