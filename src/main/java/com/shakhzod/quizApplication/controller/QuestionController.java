package com.shakhzod.quizApplication.controller;

import com.shakhzod.quizApplication.models.Question;
import com.shakhzod.quizApplication.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService service;

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return service.findAll();
    }

    @PostMapping("/post")
    public Question create(@RequestBody Question question){
        return service.save(question);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllByCategory(@PathVariable String category){
        return service.getAllByCategory(category);
    }
}
