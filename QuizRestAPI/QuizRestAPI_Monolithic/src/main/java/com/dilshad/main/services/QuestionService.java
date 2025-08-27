package com.dilshad.main.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dilshad.main.entities.Question;

public interface QuestionService {
	public ResponseEntity<Boolean> addQuestion(Question qustion);
	public ResponseEntity<List<Question>> getAllQuestions();
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category);
	public List<Question> getQuestionInRandomOrder(String category, int numQ);	
}
