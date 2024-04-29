<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
</head>
<body>
<header>

<a href="/EasyGames/"><img src="images/logo.png" width="290" height="65" alt="Logo"></a>

<div>
<form action="searchGame" method="get">
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
	<input type="text" name="search" placeholder="Search">
	<button type="submit">submit</button>
</form>
</div>

<div>
<a href="CartServlet"><img src="images/carrello.png" width="35" height="35" alt="Carrello"></a>
</div>

<div>
<a href=""><img src="images/profilo.png" width="35" height="35" alt="Carrello"></a>
</div>

</header>
</body>
</html>