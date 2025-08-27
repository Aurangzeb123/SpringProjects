package com.dilshad.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dilshad.main.entities.QuestionWrapper;
import com.dilshad.main.entities.Quiz;
import com.dilshad.main.entities.Response;
import com.dilshad.main.feign.QuizInterface;
import com.dilshad.main.repositories.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	QuizInterface quizInterface;
	
	@Autowired
	QuizRepository quizRepository;
	
	@Override
	public ResponseEntity<Quiz> createQuiz(String title, String category, int numQ) {
		
		List<Integer> questions=quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizRepository.save(quiz);
		return new ResponseEntity<Quiz>(quiz, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		Optional<Quiz> quiz=quizRepository.findById(id);
		List<Integer> questionIds=quiz.get().getQuestions();
		List<QuestionWrapper> questions=quizInterface.getQuestionsForQuiz(questionIds).getBody();			
			
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> calculateScore(int id, List<Response> responses) {
		int right=quizInterface.getScore(responses).getBody();
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}

}
