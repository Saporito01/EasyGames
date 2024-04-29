<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.easygames.model.bean.Game"%>
    
<%
Game game = (Game) request.getAttribute("game");
%>
    
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title><%=game.getName()%></title>
</head>
<body>

<%@ include file="/fragment/header.jsp"%>

<div class="main">

<div class="data">
<div class="image"><img src="getCover?id=<%=game.getId()%>" alt="copertina"></div>
<div class="panel">
<h1><%=game.getName()%></h1>
Prezzo: <%=String.format(java.util.Locale.US,"%.2f",game.getPrice())%>&euro;<br>
Piattaforma: <%=game.getPlatf()%><br>
Descrizione. <%=game.getDesc()%><br>
<div class="buttons">
<a href="CartServlet?id=<%=game.getId()%>"><button><img src="./images/carrello.png" width="35" height="35" alt="carrello"></button></a>
<button>Acquista ora</button>
</div>

</div>
</div>

</div>

<%@ include file="/fragment/footer.jsp"%>

</body>
</html>