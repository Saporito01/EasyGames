<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Game"%>

<%
Collection<?> model = (Collection<?>) request.getAttribute("orderedGames");
if(model == null) {
	request.getRequestDispatcher("/orderedGames").forward(request, response);
	return;
}
%>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/png" href="../images/logo_scheda.png"/>
<meta charset="UTF-8">
<title>Gestione giochi</title>
</head>
<body>

<header>
<a href="/EasyGames/"><img src="../images/logo.png" width="290" height="65" alt="Logo"></a>

<div class="search-bar">
<form action="../searchGame" method="get">
	<select name="piattaforma">
	<option value="tutto">Tutto</option>
	<option value="origin">Origin</option>
	<option value="steam">Steam</option>
	<option value="battle.net">Battle.net</option>
	<option value="ubisoft">Ubisoft</option>
	<option value="xbox one">XBOX One</option>
	<option value="xbox x/s">XBOX Series X/S</option>
	<option value="ps4">PS4</option>
	<option value="ps5">PS5</option>
	<option value="nintendo switch">Nintendo Switch</option>
	<option value="epic games">Epic Games</option>
	</select>
	<input type="text" name="adminSearch" placeholder="Search">
	<button type="submit"><i class="fa fa-search icon-search">Search</i></button>
</form>
</div>

</header>

<div id="main">
<div class="list-item">
<%
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<div class="item">
<a href="../getGame?idAdmin=<%=item.getId()%>"><img src="../getCover?id=<%=item.getId()%>" width="381" height="218">
<span class="title"><%=item.getName()%></span>
</a>
</div>

<%
		}
	}
%>
</div>

<a href="gestione.jsp"><button>TORNA ALLA HOME ADMIN</button></a>
</div>

</body>
</html>