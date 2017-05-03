<%@ page import="java.util.*, com.airline.models.*"
%>
<!DOCTYPE html>
<html>
<head>

<!-- <link rel="stylesheet" href="resources/css/jpaStyles.css" /> -->
<title>Flights List</title>

</head>

<body>

	<h1>List of Flights</h1>
	
	<table>
		<tr>
			<th>From</th>
			<th>To</th>
			<th>Time</th>
			<th>Price</th>
			<th>Airplane</th>
			<th>Seating</th>
			<th>Number of pilots</th>
			<th>Pilot names</th>
		</tr>
		<%
			List<Flight> fList = (List<Flight>) request.getAttribute("flight_list");
			for (Integer i = 0; i < fList.size(); i++) {
		%>
			<tr>
				<td><%= fList.get(i).getFlightOrigin() %></td>
				<td><%= fList.get(i).getFlightDestination() %></td>
				<td><%= fList.get(i).getFlightTime() %></td>
				<td><%= fList.get(i).getPrice() %></td>				
			</tr>
		<%				
			}
		%>
		
	</table>

</body>
</html>