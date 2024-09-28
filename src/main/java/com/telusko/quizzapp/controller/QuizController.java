package com.telusko.quizzapp.controller;

import com.telusko.quizzapp.model.QuestionWrapper;
import com.telusko.quizzapp.model.ResponseQuiz;
import com.telusko.quizzapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService service;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int numQ,
            @RequestParam String title
    ) {
        return service.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(
            @PathVariable Integer id
    ) {
        return service.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(
            @PathVariable("id") Integer idQuiz,
            @RequestBody List<ResponseQuiz> responseQuizs
    ) {
        return service.calculateResult(idQuiz, responseQuizs);
    }
}
