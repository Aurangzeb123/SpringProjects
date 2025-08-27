package com.dilshad.dao;

import java.sql.Connection;
import com.dilshad.entity.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
	
	private Connection conn;

	public TodoDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addTodo(String name, String todo, String status)
	{
		boolean f=false;
		try {
			String query="INSERT INTO Entity(name, todo, status) VALUES(?, ?, ?)";

			PreparedStatement ps=conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, todo);
			ps.setString(3, status);
			
			int count = ps.executeUpdate();
			if(count>0) f=true;
			else f=false;
			
		} catch (SQLException e) {
			f=false;
			e.printStackTrace();
		}
		
		
		return f;
	}
	
	public List<TodoDetails> getTodo()
	{		
		TodoDetails t=null;
		List<TodoDetails> list=new ArrayList();
		
		try {
			String query="SELECT * FROM Entity";
			
			PreparedStatement ps=conn.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int ID=rs.getInt(1);
				String name=rs.getString(2);
				String todo=rs.getString(3);
				String status=rs.getString(4);
				
				t=new TodoDetails();
				t.setID(ID);
				t.setName(name);
				t.setTodo(todo);
				t.setStatus(status);
				
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public TodoDetails getTodoById(int id)
	{
		TodoDetails t=null;
		
		try {
			String query="SELECT * FROM Entity WHERE ID=?";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				t=new TodoDetails();
				t.setID(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setTodo(rs.getString(3));
				t.setStatus(rs.getString(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
	
	public boolean updateTodo(int id, TodoDetails t)
	{
		boolean f=false;
		try {
			String query="UPDATE Entity SET name=?, todo=?, status=? WHERE ID=?";
			
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setString(1, t.getName());
			ps.setString(2, t.getTodo());
			ps.setString(3, t.getStatus());
			ps.setInt(4, id);
			
			int count=ps.executeUpdate();
			if(count>0) f=true;
			else f=false;
		} catch (Exception e) {
			f=false;
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean deleteTodo(int id)
	{
		boolean f=false;
		
		try 
		{
			String query="DELETE from Entity WHERE ID=?";
			
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			
			int count=ps.executeUpdate();
			if(count>0) f=true;
			else f=false;
			
		} catch (Exception e) {
			f=false;
			e.printStackTrace();
		}
		
		
		return f;
	}

}








