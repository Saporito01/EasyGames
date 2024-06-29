<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.easygames.model.bean.Game"%>
    
<%
Game game = (Game) request.getAttribute("game");
int qt = (Integer) request.getAttribute("quantità");
%>
    
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/gamePage_style.css"/>
<title><%=game.getName()%></title>
</head>
<body>

<%@ include file="/fragment/header.jsp"%>

<div class="main">

<div class="image"><img src="getCover?id=<%=game.getId()%>" alt="copertina"></div>

<div class="panel">
<h1><%=game.getName()%></h1><%=game.getPlatf()%>

<div id="container-button">
<%if(qt != 0){%>
<div class="cart-button"><a href="CartServlet?id=<%=game.getId()%>&azione=aggiungi"><button><img src="./images/carrello.png" width="35" height="35" alt="carrello"></button></a></div>
<%}else{%>
<div class="cart-button"><a><button>Non Disponibile</button></a></div>
<%}%>
<div class="price"><%=String.format(java.util.Locale.US,"%.2f",game.getPrice())%>&euro;</div>
</div>

<div class="description"><%=game.getDesc()%></div>

</div>

</div>

<%@ include file="/fragment/footer.jsp"%>

</body>
</html>