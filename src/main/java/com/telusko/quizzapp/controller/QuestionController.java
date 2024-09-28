package com.telusko.quizzapp.controller;


import com.telusko.quizzapp.model.Question;
import com.telusko.quizzapp.service.QuestionService;
import com.telusko.quizzapp.utils.QuestionLevels;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> getQuestionByCategory(
           @PathVariable("cat") String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("load-questions")
    public void loadQuestion() {
        List<Question> questions = List.of(
                Question.builder()
                        .questionTitle("What's is the Docker containerization?")
                        .option1("xxxxxxxxxxxx")
                        .option2("xxxxxxxxxxxx")
                        .option3("xxxxxxxxxxxx")
                        .option4("This is a containerization by Image Docker")
                        .rightAnswer("This is a containerization by Image Docker")
                        .difficultLevel(QuestionLevels.EASY.getLevel())
                        .category("DevOps")
                        .build(),
                Question.builder()
                        .questionTitle("What's is the Java Programing?")
                        .option1("xxxxxxxxxxxx")
                        .option2("xxxxxxxxxxxx")
                        .option3("xxxxxxxxxxxx")
                        .option4("Java is a high-level, object-oriented programming language created by Sun Microsystems employees James Gosling and Patrick Naughton, with support from Bill Joy, currently presented on May 23, 1995 at SunWorld.")
                        .rightAnswer("Java is a high-level, object-oriented programming language created by Sun Microsystems employees James Gosling and Patrick Naughton, with support from Bill Joy, currently presented on May 23, 1995 at SunWorld.")
                        .difficultLevel(QuestionLevels.EASY.getLevel())
                        .category("Java")
                        .build()
        );
        questionService.saveAll(questions);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(
            @RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
