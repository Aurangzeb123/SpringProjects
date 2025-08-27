package com.dilshad.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.dilshad.main.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>
{
	List<Question> findByCategory(String category);
		
	@NativeQuery(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY rand() LIMIT :numQ")
	List<Integer> findQuestionInRandomOrder(String category, int numQ);
}
