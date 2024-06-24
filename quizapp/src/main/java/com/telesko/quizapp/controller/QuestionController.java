package com.telesko.quizapp.controller;

import com.telesko.quizapp.model.Question;
import com.telesko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {


    @Autowired
    QuestionService questionService;

//get all question
    @GetMapping("all-questions")
    public ResponseEntity<List<Question>> getAllQuestion() {

        return questionService.getAllQuestions();
    }

    @GetMapping("getBy/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable int id) {

        return questionService.getQuestionById(id);
    }

    @GetMapping("getByCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {

        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("save")
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {

        return questionService.saveQuestion(question);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {

       return questionService.deleteQuestion(id);


    }

}
