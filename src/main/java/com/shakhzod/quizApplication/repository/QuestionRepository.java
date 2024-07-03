package com.shakhzod.quizApplication.repository;

import com.shakhzod.quizApplication.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByCategory(String category);

//    @Query(value = "select * from question q where q.category = : category order by random() limit :numQ", nativeQuery = true)
    @Query(value = "Select * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, Integer numQ);
}
