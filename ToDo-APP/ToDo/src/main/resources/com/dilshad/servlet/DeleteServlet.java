package com.dilshad.servlet;

import java.io.IOException;
import java.sql.Connection;

import com.dilshad.dao.TodoDAO;
import com.dilshad.db.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		
		System.out.println("id = " + id);
		Connection conn=DBConnect.getConncetion();
		TodoDAO dao=new TodoDAO(conn);
		
		boolean f=dao.deleteTodo(id);
		
		HttpSession session=req.getSession();
		
		if(f) {
			session.setAttribute("sucMsg", "Todo Deleted successfully");
			resp.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("failMsg", "Failed to delete Todo");
			resp.sendRedirect("index.jsp");
		}
	}

}
