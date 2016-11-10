<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%@page import="com.lastminute.flightsearch.model.Airport"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lastminute</title>
</head>
<body>
<h2>Bienvenido al buscador de vuelos!</h2>
<form method="get" action=FlightInformation>
Seleccione la fecha de salida:
<jsp:useBean id="departureDate" type="java.lang.String" scope="session"/>
<input type="date" name="departureDate" value="<%=departureDate%>">
<br/>
<jsp:useBean id="airports" type="java.util.ArrayList" scope="session"/>
Seleccione el aeropuerto de origen:
<select name="originAirport">
<%for(int i=0;i<airports.size();i++){
	Airport airport = (Airport) airports.get(i);%>
	<option value="<%=airport%>"><%=airport%></option>
<%}%>
</select>
<br/>
Seleccione el aeropuerto de destino:
<select name="destinationAirport">
<%for(int i=0;i<airports.size();i++){
	Airport airport = (Airport) airports.get(i);
	String selected="";
	if(i==2) selected="selected";%>
	<option value="<%=airport%>" <%=selected%>><%=airport%></option>
<%}%>
</select>
<br/>
Numero de adultos:
<input type="text" name="adultPassengers" value="1">
<br/>
Numero de niños:
<input type="text" name="childPassengers" value="0">
<br/>
Numero de bebés:
<input type="text" name="infantPassengers" value="0">
<br/>
<button type="submit">Buscar!</button>
</form>
</body>
</html>
