package com.shakhzod.quizApplication.services;

import com.shakhzod.quizApplication.models.Question;
import com.shakhzod.quizApplication.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository repository;

    public ResponseEntity<List<Question>> findAll() {
        try {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public Question save(Question question) {
        return repository.save(question);
    }

    public ResponseEntity<List<Question>> getAllByCategory(String category) {
        return new ResponseEntity<>(repository.findAllByCategory(category), HttpStatus.OK);
    }
}
