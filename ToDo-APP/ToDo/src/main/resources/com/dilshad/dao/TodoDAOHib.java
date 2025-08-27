package com.dilshad.dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import com.dilshad.entity.TodoDetails;

import org.hibernate.SessionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.dilshad.entity.TodoDetailsHib;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TodoDAOHib {
	Configuration cfg=new Configuration().configure("com/dilshad/config/hibernate.cfg.xml");
	
	SessionFactory sessionFactory=cfg.buildSessionFactory();
	Session session=sessionFactory.openSession();
	Transaction transaction=session.beginTransaction();
	
	public boolean addTodo(String name, String todo, String status)
	{
		boolean f=false;
		
		TodoDetailsHib t=new TodoDetailsHib();
		//t.setID(50); --> it will through PersistentObjectException: detached entity
		t.setName(name);
		t.setStatus(status);
		t.setTodo(todo);
		
		try {
			session.persist(t);
			transaction.commit();
			f=true;
		}catch(Exception e)
		{
			f=false;
			e.printStackTrace();
			transaction.rollback();
		}
		
		return f;
	}
	
	public List<TodoDetails> getTodo()
	{		
		//TodoDetails t=null;
		List<TodoDetailsHib> list=new ArrayList();
		List<TodoDetails> ret_list=new ArrayList();
		
		return ret_list;
	}
	
	public TodoDetails getTodoById(int id)
	{
		TodoDetails t=null;
		
		return t;
	}
	
	public boolean updateTodo(int id, TodoDetails t)
	{
		boolean f=false;
		
		return f;
	}
	
	public boolean deleteTodo(int id)
	{
		boolean f=false;		
		
		return f;
	}
}
