package com.kevin.quizz.app.repository;

import com.kevin.quizz.app.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option,Integer> {
    boolean existsByIdAndIsCorrectTrue(Long selectedOptionId);
}
