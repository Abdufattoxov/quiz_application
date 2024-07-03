package com.shakhzod.quizApplication.services;

import com.shakhzod.quizApplication.models.Question;
import com.shakhzod.quizApplication.models.QuestionDto;
import com.shakhzod.quizApplication.models.Quiz;
import com.shakhzod.quizApplication.models.Response;
import com.shakhzod.quizApplication.repository.QuestionRepository;
import com.shakhzod.quizApplication.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;


    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("succes", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDto>> getById(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestion();
        List<QuestionDto> questionforUser = new ArrayList<>();
        for (Question q : questionFromDB) {
            QuestionDto questionDto = new QuestionDto(q.getId(),q.getCategory(),q.getQuestionTitle(),q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionforUser.add(questionDto);
        }
        return new ResponseEntity<>(questionforUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizs = quizRepository.findById(id);
        List<Question> questions = quizs.get().getQuestion();
        int right = 0;
        int i = 0;
        for (Response r : responses) {
            if(r.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);

    }
}
