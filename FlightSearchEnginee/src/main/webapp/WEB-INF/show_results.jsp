<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%@page import="com.lastminute.flightsearch.model.SearchResultFlight"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lastminute</title>
</head>
<body>
<jsp:useBean id="searchResultFlightList" type="java.util.ArrayList" scope="request"/>

<%if(searchResultFlightList.size() > 0){%>
<h2>Vuelos encontrados:</h2>
<%for(int i=0;i<searchResultFlightList.size();i++){
	SearchResultFlight info = (SearchResultFlight)searchResultFlightList.get(i);%>
	<p>CODE: <%=info.getFlightCode()%></p>
	<p>PRICE: <%=info.getTotalFlightPrice()%> Euros</p>
	<p>-----------------------</p>
<%}} else {%>	
<p>No hay vuelos disponibles :(</p>
<%}%>
</body>
</html>