<%@ page import="com.dilshad.dao.TodoDAO" %>
<%@ page import="com.dilshad.dao.TodoDAOHib" %>
<%@ page import="com.dilshad.db.DBConnect" %>
<%@ page import="com.dilshad.entity.TodoDetails" %>
<%@ page import="com.dilshad.entity.TodoDetailsHib" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="Component/all_css.jsp"%>
</head>
<body>
	<%@include file="Component/navbar.jsp"%>
	<h1 class="text-center text-success">TODO-APP</h1>
	<%
		String sucMsg=(String)session.getAttribute("sucMsg");
		if(sucMsg != null){
	%> <div class="alert alert-success" role="alert"><%=sucMsg%></div>
	<%
	    session.removeAttribute("sucMsg");
	}	
	%>
	
	<%
		String failMsg=(String)session.getAttribute("failMsg");
		if(failMsg != null){
	%> <div class="alert alert-danger" role="alert"><%=failMsg%></div>
	<%
	    session.removeAttribute("failMsg");
	}	
	%>

	<div class="container">
		<table class="table table-striped" border="1px">
			<thead class="bg-success text-white">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Name</th>
					<th scope="col">Todo</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
					TodoDAO dao=new TodoDAO(DBConnect.getConncetion());
					List<TodoDetails> list=dao.getTodo();
					
					for(TodoDetails t: list){
				%>
				<tr>
					<th scope="row"><%=t.getID() %></th>
					<td><%=t.getName() %></td>
					<td><%=t.getTodo() %></td>
					<td><%=t.getStatus() %></td>
					<td>
						<a href="edit.jsp?id=<%=t.getID()%>" class="btn btn-sm btn-success">Edit</a>
						<a href="delete?id=<%=t.getID()%>" class="btn btn-sm btn-danger">Delete</a>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

	</div>



</body>
</html>