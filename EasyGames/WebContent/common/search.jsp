<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.easygames.model.bean.Game"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<title>Risultati</title>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

<%
	Collection<?> model = (Collection<?>) request.getAttribute("gameSearch");
%>

<div class="main">

<%
	if(model.isEmpty()){
%>
<h2 class="align">NESSUN RISULTATO</h2>
<%
	}
	else{
%>
<h2 class="align">RISULTATI</h2>
<%}%>

<div class="list_item">

<%
if(model != null && model.size() > 0) {
	Iterator<?> it = model.iterator(); 
	while(it.hasNext()) {
		Game item = (Game)it.next();
%>

<div class="item">
<a href="getGame?idHome=<%=item.getId()%>">
<img src="getCover?id=<%=item.getId()%>" width="381" height="218" alt="copertina">
</a>
<span class="title"><%=item.getName()%></span><span class="price"><%=String.format(java.util.Locale.US,"%.2f",item.getPrice())%>&euro;</span>
</div>

<%
		}
	}
%>

</div>

</div>

<%@include file="/fragment/footer.jsp" %>

</body>
</html>