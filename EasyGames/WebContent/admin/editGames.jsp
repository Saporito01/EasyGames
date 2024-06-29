<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Game"%>

<%
Game item = (Game) request.getAttribute("game");
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/adminPage_style.css"/>
<title>Modifica gioco</title>
</head>
<body>

<header class="header-admin">
<a href="/EasyGames/admin/gestione.jsp"><img src="images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<div class="main">

<form class="add-form" action="editGame" enctype="multipart/form-data" method="post">

<label for="id">Id:</label>
<input id="id" type="text" name="id" value="<%=item.getId()%>" readonly="readonly"><br><br>

<label for="nome">Nome:</label>
<input id="nome" type="text" name="nome" value="<%=item.getName()%>" required><br><br>

<label for="descrizione">Descrizione:</label>
<textarea id="descrizione" name="descrizione" required><%=item.getDesc()%></textarea><br><br>

<label for="piattaforma">Piattaforma: <%=item.getPlatf()%></label><br>
<select id="piattaforma" name="piattaforma" required>
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
	</select><br><br>
	
<label for="quantita">Quantità:</label>
<input id="quantita" type="number" name="quantita" min="1" value="<%=item.getQt()%>" required><br><br>

<label for="prezzo">Prezzo:</label>
<input id="prezzo" type="text" name="prezzo" value="<%=item.getPrice()%>" required><br><br>

<label for="copertina">Carica nuova copertina:</label>
<input id="copertina" class="file" type="file" name="copertina" value="" maxlength="255"><br><br>

<div id="form-button">
<input type="submit" value="Aggiorna">
<input type="reset" value="Reset">
</div>

<div id="remove-button"><a href="editGame?idRemove=<%=item.getId()%>"><button>Rimuovi</button></a></div>

</form>

</div>

</body>
</html>