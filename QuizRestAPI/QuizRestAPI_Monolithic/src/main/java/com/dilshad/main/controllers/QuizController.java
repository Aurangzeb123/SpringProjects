package com.dilshad.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dilshad.main.entities.QuestionWrapper;
import com.dilshad.main.entities.Quiz;
import com.dilshad.main.entities.Response;
import com.dilshad.main.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/createQuiz")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String title, @RequestParam String category, @RequestParam int numQ)
	{
		System.out.println(title+" "+category+" "+numQ);
		return quizService.createQuiz(title, category, numQ);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id)
	{
		System.out.println("id="+id);
		return quizService.getQuiz(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses)
	{
		return quizService.calculateScore(id, responses);
	}
	
}
