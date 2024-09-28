package com.telusko.quizzapp.service;

import com.telusko.quizzapp.dao.QuestionDao;
import com.telusko.quizzapp.dao.QuizDao;
import com.telusko.quizzapp.model.Question;
import com.telusko.quizzapp.model.QuestionWrapper;
import com.telusko.quizzapp.model.Quiz;
import com.telusko.quizzapp.model.ResponseQuiz;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizDao repo;
    private final QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            // Save the new Quiz into DB
            List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);
            repo.save(
                    Quiz.builder()
                            .title(title)
                            .questions(questions)
                            .build()
            );
            return new ResponseEntity<>("Created!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something is Wrong", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = repo.findById(id);
        //List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> result =quiz.get().getQuestions()
                .stream()
                .map(question -> QuestionWrapper.builder()
                        .id(question.getId())
                        .questionTitle(question.getQuestionTitle())
                        .option1(question.getOption1())
                        .option2(question.getOption2())
                        .option3(question.getOption3())
                        .option4(question.getOption4())
                        .build())
                .toList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer idQuiz, List<ResponseQuiz> responseQuizs) {
        Optional<Quiz> quiz = repo.findById(idQuiz);
        int right = 0;
        int i = 0;
        if (quiz.isPresent()) {
            List<Question> questions  = quiz.get().getQuestions();
            if (!questions.isEmpty()) {
                for (ResponseQuiz response: responseQuizs) {
                    if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                        right+=25;
                    }
                    i++;
                }
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);


    }
}
