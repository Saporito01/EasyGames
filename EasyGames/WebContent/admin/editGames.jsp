<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Game"%>

<%
Game item = (Game) request.getAttribute("game");
%>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/png" href="../images/logo_scheda.png"/>
<meta charset="UTF-8">
<title>Modifica gioco</title>
</head>
<body>

<header>
<a href="/EasyGames/"><img src="images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<div class="main">

<form action="editGame" enctype="multipart/form-data" method="post">

Id: <input type="text" name="id" value="<%=item.getId()%>" readonly="readonly"><br><br>

Nome: <input type="text" name="nome" value="<%=item.getName()%>" required><br><br>

Descrizione: <textarea name="descrizione" required><%=item.getDesc()%></textarea><br><br>

Piattaforma: <%=item.getPlatf()%>
	<select name="piattaforma" required>
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
	
Quantità: <input type="number" name="quantita" min="1" value="<%=item.getQt()%>" required><br><br>

Prezzo: <input type="text" name="prezzo" value="<%=item.getPrice()%>" required><br><br>

Carica nuova copertina:
<input class="file" type="file" name="copertina" value="" maxlength="255"><br><br>

<input type="submit" value="Aggiorna">
<input type="reset" value="Reset">

</form>

<br>

<a href="editGame?idRemove=<%=item.getId()%>"><button>Rimuovi</button></a>

<a href="admin/gestione.jsp"><button>Torna alla Home Admin</button></a>

</div>

</body>
</html>