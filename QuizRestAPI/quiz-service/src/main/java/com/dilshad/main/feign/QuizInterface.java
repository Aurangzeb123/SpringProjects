package com.dilshad.main.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dilshad.main.entities.QuestionWrapper;
import com.dilshad.main.entities.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface 
{
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQ);
	
	@PostMapping("question/quizQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> question_id_list);
	
	@PostMapping("question/submit")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
