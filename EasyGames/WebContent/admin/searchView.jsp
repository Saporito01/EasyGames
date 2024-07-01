<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Game"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="./css/style.css"/>
<title>Risultati</title>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

<div class="main">

<h2>Risultati</h2>

<div class="list_item">
<%
Collection<?> model = (Collection<?>) request.getAttribute("gameSearch");
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<div class="item">
<a href="getGame?idAdmin=<%=item.getId()%>">
<img src="getCover?id=<%=item.getId()%>" width="350" height="200" alt="copertina">
</a>
<span class="title"><%=item.getName()%></span>
</div>

<%
		}
	}
%>

</div>
</div>

</body>
</html>