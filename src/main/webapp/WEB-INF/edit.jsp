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
<title>edit</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h1 class="mb-0">Edit Table</h1>
			<a href="/logout" class="btn btn-link">LOGOUT</a>

		</div>

		<form:form action="/tables/${table.id }/edit" method="post"
			modelAttribute="table">
			<input type="hidden" name="_method" value="put">
			<form:errors class="text-danger" path="*" />
			<div class="mb-3">
				<form:label path="name" class="form-label">Name</form:label>
				<form:input path="name" class="form-control" />
			</div>
			<div class="mb-3">
				<form:label path="numberGuest" class="form-label">Number of Guests</form:label>
				<form:input type="number" path="numberGuest" class="form-control" />
			</div>
			<div class="mb-3">
				<form:label path="description" class="form-label">Description</form:label>
				<form:textarea path="description" class="form-control" rows="3" />
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="/home" class="btn btn-secondary">Cancel</a>
		</form:form>
			<form action="/tables/${table.id }" method="post" >
   	<input type="hidden" name="_method" value="delete">
    <input type="submit" value="Delete Table" class="btn btn-danger m-3">
   	</form>
	</div>


</body>
</html>