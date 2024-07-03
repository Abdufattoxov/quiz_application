package com.shakhzod.quizApplication.models;

public record QuestionDto(
         Integer id,
         String category,
         String questionTitle,
         String option1,
         String option2,
         String option3,
         String option4
) {
}
