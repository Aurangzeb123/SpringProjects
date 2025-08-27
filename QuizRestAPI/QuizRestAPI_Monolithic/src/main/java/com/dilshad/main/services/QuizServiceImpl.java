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
import com.dilshad.main.entities.Quiz;
import com.dilshad.main.entities.Response;
import com.dilshad.main.repositories.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuizRepository quizRepository;
	
	@Override
	public ResponseEntity<Quiz> createQuiz(String title, String category, int numQ) {
		Quiz quiz=new Quiz();
		
		quiz.setTitle(title);
		List<Question> questions=questionService.getQuestionInRandomOrder(category, numQ);
		quiz.setQuestions(questions);
		
		quizRepository.save(quiz);
		return new ResponseEntity<Quiz>(quiz, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		Optional<Quiz> quiz=quizRepository.findById(id);
		List<Question> questions=quiz.get().getQuestions();
		
		List<QuestionWrapper> userQuestionList=new ArrayList<>();
		
		for(Question q: questions)
		{
			QuestionWrapper qw=new QuestionWrapper();
			qw.setId(q.getId());
			qw.setQuestion(q.getQuestion());
			qw.setOption1(q.getOption1());
			qw.setOption2(q.getOption2());
			qw.setOption3(q.getOption3());
			qw.setOption4(q.getOption4());
			
			System.out.println(q.toString());
			userQuestionList.add(qw);
		}
			
			
		return new ResponseEntity<>(userQuestionList, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Integer> calculateScore(int id, List<Response> responses) {
		Optional<Quiz> quiz_optional=quizRepository.findById(id);
		Quiz quiz=quiz_optional.get();
		List<Question> questions=quiz.getQuestions();
		
		int right=0;
		int i=0;
		
		for(Response r: responses)
		{
			if(r.getResponse().equals(questions.get(i).getAnswer()))
				right++;
			i++;
		}
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}

}
