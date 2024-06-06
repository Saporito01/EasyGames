<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.easygames.model.bean.Account, it.easygames.model.bean.Ordine"%>
    
<%
ArrayList<String> accountList = (ArrayList<String>) request.getAttribute("accounts");
if(accountList == null) {
	response.sendRedirect("../AdminOrder");
	return;
}
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="./css/adminPage_style.css"/>

<style>
table, th, td {
  border:1px solid black;
}

</style>
<title>Ordini</title>
</head>
<body>

<header class="header-admin">
<a href="/EasyGames/admin/gestione.jsp"><img src="./images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<form action="AdminOrder" method="get">
<input type="date" name="data1">
<input type="date" name="data2">
<select name="account">
<option value="tutto">Tutti</option>

<%
for(int i=0; i<accountList.size(); i++){
%>
<option value="<%=accountList.get(i)%>"><%=accountList.get(i)%></option>
<%
}
%>

</select>
<input type="submit" value="Cerca">
</form>

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

</body>
</html>