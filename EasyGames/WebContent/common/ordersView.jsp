<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.easygames.model.bean.Account, it.easygames.model.bean.Ordine"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
table, th, td {
  border:1px solid black;
}
</style>

<title>Ordini</title>
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

<form action="UserOrder" method="get">
<input type="date" name="data1">
<input type="date" name="data2">
<input type="submit" value="Cerca">
</form>
<h1>ULTIMO MESE</h1>
<table style="width:100%">
  <tr>
    <th>Codice</th>
    <th>Data</th>
    <th>Account</th>
  </tr>
  <tr>

<%
Collection<?> ordini = (Collection<?>) request.getAttribute("orderList");
if(ordini != null && ordini.size() > 0) {
	Iterator<?> it = ordini.iterator(); 
	while(it.hasNext()) {
		Ordine item = (Ordine) it.next();
%>
    <td><%=item.getCodice()%></td>
    <td><%=item.getData()%></td>
    <td><%=item.getAccount()%></td>
  </tr>
<%
	}
}
%>

</table>

<%@include file="/fragment/footer.jsp" %>

</body>
</html>