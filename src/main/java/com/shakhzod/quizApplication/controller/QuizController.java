package com.shakhzod.quizApplication.controller;

import com.shakhzod.quizApplication.models.QuestionDto;
import com.shakhzod.quizApplication.models.Response;
import com.shakhzod.quizApplication.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam Integer numQ,@RequestParam String title){
            return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDto>> getById(@PathVariable Integer id){
        return quizService.getById(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}
