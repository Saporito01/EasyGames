<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.easygames.model.bean.Game"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<title>Risultati</title>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

<div id="main">
<h2 class="align">Risultati</h2>
<div class="list-item">
<%
	Collection<?> model = (Collection<?>) request.getAttribute("gameSearch");
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<div class="item">
<a href="getGame?idHome=<%=item.getId()%>">
<img src="getCover?id=<%=item.getId()%>" width="381" height="218" alt="copertina">
</a>
<span class="title"><%=item.getName()%></span><span class="price"><%=item.getPrice()%>&#8364</span>
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