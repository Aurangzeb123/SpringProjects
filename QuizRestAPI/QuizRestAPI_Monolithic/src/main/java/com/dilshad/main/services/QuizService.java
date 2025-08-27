package com.dilshad.main.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dilshad.main.entities.QuestionWrapper;
import com.dilshad.main.entities.Quiz;
import com.dilshad.main.entities.Response;

public interface QuizService {
	public ResponseEntity<Quiz> createQuiz(String title, String category, int numQ);
	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id);
	public ResponseEntity<Integer> calculateScore(int id, List<Response> responses);

}
