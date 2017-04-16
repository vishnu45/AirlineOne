<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>World Adventures Airlines</title>
	<link rel="stylesheet" href="resources/css/normalize.css">
	<link rel="stylesheet" href="resources/css/theme.css">
</head>
<body>
	<div class="container">
		<div class="title">Add a passenger</div>

		<!-- SCRIPLET -->
		<%
			if (request.getAttribute("errors") != null) {
		%>
			<fieldset>
				<legend>Errors</legend>
				<ul>
					<% if (request.getAttribute("firstName_errors") != null) { %>
						<li class="error">First name error</li>
					<% } %>

					<% if (request.getAttribute("lastName_errors") != null) { %>
						<li class="error">Last name error</li>
					<% } %>

					<% if (request.getAttribute("date_format_error") != null) { %>
						<li class="error">Date of birth invalid</li>
					<% } %>
				</ul>
			</fieldset>
		<% } %>
		<fieldset>
			<legend>Passenger details</legend>
			<form action="AddPassenger" method="post">
				<div class="inputfield">
					<label for="first-name" class="inputlabel">First name: </label> <input
					name="first-name" type="text" value="<%= request.getAttribute("first_name") %>"></input>
				</div>

				<div class="inputfield">
					<label for="last-name" class="inputlabel">Last name: </label> <input
					name="last-name" type="text" value="<%= request.getAttribute("last_name") %>"></input>
				</div>

				<div class="inputfield">
					<label for="dob" class="inputlabel">Date of birth: </label> <input
					name="dob" type="text" value="<%= request.getAttribute("dob") %>"></input>
				</div>

				<div class="inputfield">
					<label for="gender" class="inputlabel">Gender: </label> <select
					name="gender">
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</div>
		</fieldset>

		<div class="inputfield" id="submitfield">
			<input type="submit" id="submitBtn" value="Add new passenger"></input>
		</div>

	</form>
</div>

</body>
</html>