package com.dilshad.main.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dilshad.main.entities.Question;
import com.dilshad.main.repositories.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService
{
	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public ResponseEntity<Boolean> addQuestion(Question qustion) 
	{
		try {
			questionRepository.save(qustion);
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(true, HttpStatus.BAD_REQUEST);
		}		
	}

	@Override
	public ResponseEntity<List<Question>> getAllQuestions() 
	{
		try {
			return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) 
	{
		try {
			return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<Question> getQuestionInRandomOrder(String category, int numQ) {
		return questionRepository.findQuestionInRandomOrder(category, numQ);
	}

}
