package com.dilshad.servlet;

import java.io.IOException;
import java.sql.Connection;

import com.dilshad.dao.TodoDAO;
import com.dilshad.db.DBConnect;
import com.dilshad.entity.TodoDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update_todo")
public class Updateservlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			int id=Integer.parseInt(req.getParameter("id"));
			String name=req.getParameter("username");
			String todo=req.getParameter("todotask");
			String status=req.getParameter("status");
			
			System.out.println(name +" " + todo + " " + status + "  " + id);
			Connection conn=DBConnect.getConncetion();
			TodoDAO dao=new TodoDAO(conn);
			
			TodoDetails t=new TodoDetails();
			t.setID(id);
			t.setName(name);
			t.setTodo(todo);
			t.setStatus(status);
			
			boolean f=dao.updateTodo(id, t);
			
			HttpSession session=req.getSession();
			
			if(f) {
				session.setAttribute("sucMsg", "Todo updated successfully");
				resp.sendRedirect("index.jsp");
			}
			else {
				session.setAttribute("failMsg", "Failed to update Todo");
				resp.sendRedirect("index.jsp");
			}
	}

}













