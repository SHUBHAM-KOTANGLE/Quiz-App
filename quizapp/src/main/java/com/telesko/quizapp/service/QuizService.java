package com.telesko.quizapp.service;


import com.telesko.quizapp.model.Question;
import com.telesko.quizapp.model.Quiz;
import com.telesko.quizapp.model.Response;
import com.telesko.quizapp.payloads.QuizQuestionDto;
import com.telesko.quizapp.repository.QuestionRepository;
import com.telesko.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);
        System.out.println(questions);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuizQuestionDto>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuizQuestionDto> dtoForUser = new ArrayList<>();
        for (Question q : questionFromDB) {
            QuizQuestionDto questionDto = new QuizQuestionDto(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            dtoForUser.add(questionDto);

        }


        return new ResponseEntity<>(dtoForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();

        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRight_answer()))
                right++;

                i++;

        }
        return  new ResponseEntity<>(right,HttpStatus.OK);
    }
}
