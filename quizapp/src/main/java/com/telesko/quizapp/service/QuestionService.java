package com.telesko.quizapp.service;


import com.telesko.quizapp.model.Question;
import com.telesko.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;


    //get all question
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    //get all question by id
    public ResponseEntity<Optional<Question>> getQuestionById(int id) {
        try {
            return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(repository.findById(id), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {

        return new ResponseEntity<>(repository.getQuestionByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<Question> saveQuestion(Question question) {

        return new ResponseEntity<Question>(repository.save(question), HttpStatus.CREATED);

    }

    public ResponseEntity<String> deleteQuestion(int id) {



        try {
            Optional<Question> exquestion = repository.findById(id);
            repository.delete(exquestion.get());
            return new ResponseEntity<>("Question deleted", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);


    }
}
