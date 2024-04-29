<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Game"%>
<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/png" href="../images/logo_scheda.png"/>
<meta charset="UTF-8">
<title>Risultati</title>
</head>
<body>

<div>
<%
Collection<?> model = (Collection<?>) request.getAttribute("gameSearch");
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<a href="getGame?idAdmin=<%=item.getId()%>"><img src="getCover?id=<%=item.getId()%>" width="350" height="200" alt="copertina">
<%=item.getName()%>
</a>

<%
		}
	}
%>
</div>

<a href="gestione.jsp"><button>Torna alla Home Admin</button></a>

</body>
</html>