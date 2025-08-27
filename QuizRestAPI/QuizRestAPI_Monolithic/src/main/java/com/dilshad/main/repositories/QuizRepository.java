package com.dilshad.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dilshad.main.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
