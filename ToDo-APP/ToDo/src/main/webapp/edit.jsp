<%@ page import="com.dilshad.dao.TodoDAO" %>
<%@ page import="com.dilshad.db.DBConnect" %>
<%@ page import="com.dilshad.entity.TodoDetails" %>
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
<body style="background-color: #f0f1f2;">
	<%@ include file="Component/navbar.jsp"%>
	<div class="container">
		<div class="row p-5">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body">
						<h3 class="text-center text-success">Add Todo</h3>
						<%
							TodoDAO dao=new TodoDAO(DBConnect.getConncetion());
							int id=Integer.parseInt(request.getParameter("id"));
							TodoDetails t=dao.getTodoById(id);
						%>
						<form action="update_todo" method="POST">
							<input type="hidden" name="id" value="<%=t.getID()%>">
							<div class="form-group">
								<label for="exampleInputEmail1">Name</label> <input type="text"
									class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="username" value="<%=t.getName()%>">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Todo</label> <input type="text"
									class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="todotask" value="<%=t.getTodo()%>">
							</div>
							<div class="form-group">
								<label for="inputState">Status</label> 
								<select id="inputState" class="form-control" name="status">
									<%
										if(t.getStatus().equals("PENDING"))
										{%>
											<option value="PENDING">Pending</option>
											<option value="COMPLETE">Complete</option>
									<%}
										else
										{%>
											<option value="COMPLETE">Complete</option>
											<option value="PENDING">Pending</option>
									<%}
									%>
								</select>
							</div>

							<div class="text-center">
							<button type="submit" class="btn btn-primary">UPDATE</button>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>