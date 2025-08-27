package com.dilshad.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dilshad.main.entities.Question;
import com.dilshad.main.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController 
{
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question)
	{
		ResponseEntity<Boolean> resp=questionService.addQuestion(question);
		
		if(resp.getBody())
			return new ResponseEntity<String>("added", HttpStatus.CREATED);
		return new ResponseEntity<String>("Failed to add", HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("/addAll")
    public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions) 
	{
		int count=0;
		try 
		{
			for (Question question : questions) {
			    
	            System.out.println(question);
	            questionService.addQuestion(question);
	            count++;
	        }
			return new ResponseEntity<String>("All Questions added successfully! "+count+" questins are added", HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error in adding questions. "+count+" questins are added", HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion()
	{
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{cat}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("cat") String category)
	{
		return questionService.getQuestionsByCategory(category);
	}
}






