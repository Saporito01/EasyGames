<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.easygames.model.bean.Game"%>
    
<%
Collection<?> model = (Collection<?>) request.getAttribute("games");
if(model == null) {
	request.getRequestDispatcher("../getGame").forward(request, response);
	return;
}
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<title>EasyGames</title>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

<div class="main">

<h2>IN EVIDENZA</h2>

<div class="list_item">
<%
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<div class="item">
<a href="./getGame?idHome=<%=item.getId()%>">
<img src="./getCover?id=<%=item.getId()%>" width="381" height="218" alt="copertina">
</a>
<span class="title"><%=item.getName()%></span><span class="price"><%=String.format(java.util.Locale.US,"%.2f",item.getPrice())%>&euro;</span>
</div>

<%
		}
	}
%>
</div>

<h2 class="align">CATEGORIE</h2>
<div class="containerCat">
<div class="list_cat">
<a href="./getGame?genere=arcade"><button><img src="./images/arcade.png" alt="img"><div id="cat-title">Arcade</div></button></a>
<a href="./getGame?genere=avventura"><button><img src="./images/avventura.png" alt="img"><div id="cat-title">Avventura</div></button></a>
<a href="./getGame?genere=azione"><button><img src="./images/azione.png" alt="img"><div id="cat-title">Azione</div></button></a>
<a href="./getGame?genere=giocatore singolo"><button><img src="./images/giocatore singolo.png" alt="img"><div id="cat-title">Giocatore singolo</div></button></a>
<a href="./getGame?genere=corse"><button><img src="./images/corse.png" alt="img"><div id="cat-title">Corse</div></button></a>
<a href="./getGame?genere=sport"><button><img src="./images/sport.png" alt="img"><div id="cat-title">Sport</div></button></a>
<a href="./getGame?genere=multiplayer"><button><img src="./images/multiplayer.png" alt="img"><div id="cat-title">Multiplayer</div></button></a>
<a href="./getGame?genere=picchiaduro"><button><img src="./images/picchiaduro.png" alt="img"><div id="cat-title">Picchiaduro</div></button></a>
<a href="./getGame?genere=simulazione"><button><img src="./images/simulazione.png" alt="img"><div id="cat-title">Simulazione</div></button></a>
<a href="./getGame?genere=strategia"><button><img src="./images/strategia.png" alt="img"><div id="cat-title">Strategia</div></button></a>
<a href="./getGame?genere=fps"><button><img src="./images/fps.png" alt="img"><div id="cat-title">FPS</div></button></a>
<a href="./getGame?genere=rpg"><button><img src="./images/rpg.png" alt="img"><div id="cat-title">RPG</div></button></a>
</div>
</div>
</div>


<%@include file="/fragment/footer.jsp" %>

</body>
</html>