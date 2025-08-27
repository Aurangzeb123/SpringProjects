package com.dilshad.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dilshad.main.entities.Question;
import com.dilshad.main.entities.QuestionWrapper;
import com.dilshad.main.entities.Response;
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
	public ResponseEntity<List<Integer>> getQuestionInRandomOrder(String category, int numQ) {
		return new ResponseEntity<>(questionRepository.findQuestionInRandomOrder(category, numQ), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> question_id_list) {
		List<QuestionWrapper> questions=new ArrayList();
		
		for(int id:question_id_list)
		{
			Question q=questionRepository.findById(id).get();
			if(q!=null)
			{
				QuestionWrapper qw=new QuestionWrapper();
				qw.setId(q.getId());
				qw.setQuestion(q.getQuestion());
				qw.setOption1(q.getOption1());
				qw.setOption2(q.getOption2());
				qw.setOption3(q.getOption3());
				qw.setOption4(q.getOption4());
				questions.add(qw);
			}
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getScore(List<Response> responses) {
				
		int right=0;
		
		for(Response r: responses)
		{
			Question q=questionRepository.findById(r.getQuestion_id()).get();
			System.out.println(q.toString());
			if(q!=null) 
			{
				if(r.getResponse().equals(q.getAnswer()))
						right++;
			}	
		}
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}

}
