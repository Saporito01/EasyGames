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
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
<title>EasyGames</title>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

<div id="main">

<h2 >I pi&ugrave; venduti</h2>

<h2 class="align">I pi&ugrave; venduti</h2>
<div class="list-item">
<%
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<div class="item">
<a href="./getGame?idHome=<%=item.getId()%>">
<img src="./getCover?id=<%=item.getId()%>" width="381" height="218">
</a>
<span class="title"><%=item.getName()%></span><span class="price"><%=String.format(java.util.Locale.US,"%.2f",item.getPrice())%>&euro;</span>
</div>

<%
		}
	}
%>
</div>

<h2 class="align">Categorie</h2>
<div class="list-item">
<div class="itemCat"><a href=""><button><img src="./images/arcade.png" alt="img"><div id="cat-title">Arcade</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/avventura.png" alt="img"><div id="cat-title">Avventura</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/azione.png" alt="img"><div id="cat-title">Azione</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/giocatore singolo.png" alt="img"><div id="cat-title">Giocatore singolo</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/corse.png" alt="img"><div id="cat-title">Corse</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/sport.png" alt="img"><div id="cat-title">Sport</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/multiplayer.png" alt="img"><div id="cat-title">Multiplayer</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/picchiaduro.png" alt="img"><div id="cat-title">Picchiaduro</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/simulazione.png" alt="img"><div id="cat-title">Simulazione</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/strategia.png" alt="img"><div id="cat-title">Strategia</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/fps.png" alt="img"><div id="cat-title">FPS</div></button></a></div>
<div class="itemCat"><a href=""><button><img src="./images/rpg.png" alt="img"><div id="cat-title">RPG</div></button></a></div>

</div>
</div>


<%@include file="/fragment/footer.jsp" %>

</body>
</html>