
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
<body>
	<div class="container mt-3">
		<div class="d-flex justify-content-between">
			<div>
				<h1 class="text-primary">Open Tables</h1>

			</div>

			<a href="/home">home</a>

		</div>
		<h4>Tables</h4>
		<table class="table">
			<thead>
				<tr>
					<th>Guest Name</th>
					<th># Guests</th>
					<th>Arrived At</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${giveUpTable }" var="oneTable">
					<tr>

						<td>${oneTable.name}</td>
						<td>${oneTable.numberGuest}</td>
						<td>${oneTable.createdAt}</td>

						<td><c:if
								test="${oneTable.userGiveUp.contains(user)eq false}">
								<a href="/giveup/${oneTable.id}" class="btn btn-success m-3">Give
									Up Table</a>
							</c:if> <c:if test="${oneTable.userGiveUp.contains(user)}">
								<a href="/pickup/${oneTable.id}" class="btn btn-danger m-3">Pick
									Up Table</a>
							</c:if></td>



					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/tables/new" class="btn btn-secondary">+ new table </a>
	</div>

</body>
</html>