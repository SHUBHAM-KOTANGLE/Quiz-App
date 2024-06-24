package com.telesko.quizapp.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "quiz_questions")
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String difficulty_level;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
    private String question_title;


}
