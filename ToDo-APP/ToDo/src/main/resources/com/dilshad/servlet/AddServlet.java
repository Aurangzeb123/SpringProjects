package com.dilshad.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import com.dilshad.dao.TodoDAO;
import com.dilshad.dao.TodoDAOHib;
import com.dilshad.db.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/add_todo")
public class AddServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/// get the form values from request object.
		String name = req.getParameter("username");
		String todo = req.getParameter("todotask");
		String status = req.getParameter("status");
		
		PrintWriter out=resp.getWriter();
		
		// Process the request
		System.out.println(name + " " + todo + " " + status);
		
		/// get DB Connection
		Connection conn=DBConnect.getConncetion();
		
		// get DAO class object.
		//TodoDAO dao=new TodoDAO(conn);
		TodoDAOHib dao=new TodoDAOHib();
		boolean f=dao.addTodo(name, todo, status);
		
		HttpSession session=req.getSession();
		
		if(f) {
			session.setAttribute("sucMsg", "Todo added successfully");
			resp.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("failMsg", "Failed to add Todo");
			resp.sendRedirect("index.jsp");
		}
	}
}






