package com.dilshad.main.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dilshad.main.entities.Question;
import com.dilshad.main.entities.QuestionWrapper;
import com.dilshad.main.entities.Response;

public interface QuestionService {
	public ResponseEntity<Boolean> addQuestion(Question qustion);
	public ResponseEntity<List<Question>> getAllQuestions();
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category);
	public ResponseEntity<List<Integer>> getQuestionInRandomOrder(String category, int numQ);
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> question_id_list);
	public ResponseEntity<Integer> getScore(List<Response> responses);	
}
