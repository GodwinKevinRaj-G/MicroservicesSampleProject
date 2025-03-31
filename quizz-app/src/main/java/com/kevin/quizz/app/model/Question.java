package com.kevin.quizz.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "question_text", nullable = false)
    @JsonProperty("question_text")
    private String questionText;

    @Column(name = "category", nullable = false)
    @JsonProperty("category")
    private String category;

    @Column(name = "difficulty", nullable = false)
    @JsonProperty("difficulty")
    private String difficulty;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("options")
    private List<Option> options;

}
