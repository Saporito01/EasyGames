<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="../images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="../css/adminPage_style.css"/>
<title>Inserimento giochi</title>
</head>
<body>

<header class="header-admin">
<a href="/EasyGames/admin/gestione.jsp"><img src="../images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<div class="main">

<form class="add-form" action="../addGame" enctype="multipart/form-data" method="post">

<label for="id">Id:</label>
<input id="id" type="text" name="id" required><br><br>

<label for="nome">Nome:</label>
<input id="nome" type="text" name="nome" required><br><br>

<label for="descrizione">Descrizione:</label>
<textarea id="descrizione" name="descrizione" required></textarea><br><br>

<label for="piattaforma">Piattaforma:</label>
<select id="piattaforma" name="piattaforma" required>
	<option value="">Tutto</option>
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

<input type="checkbox" name="genere" value="arcade"> Arcade <br>
<input type="checkbox" name="genere" value="avventura"> Avventura <br>
<input type="checkbox" name="genere" value="azione"> Azione <br>
<input type="checkbox" name="genere" value="giocatore singolo"> Giocatore singolo <br>
<input type="checkbox" name="genere" value="corse"> Corse <br>
<input type="checkbox" name="genere" value="sport"> Sport <br>
<input type="checkbox" name="genere" value="multiplayer"> Multiplayer <br>
<input type="checkbox" name="genere" value="picchiaduro"> Picchiaduro <br>
<input type="checkbox" name="genere" value="simulazione"> Simulazione <br>
<input type="checkbox" name="genere" value="strategia"> Strategia <br>
<input type="checkbox" name="genere" value="fps"> FPS <br>
<input type="checkbox" name="genere" value="rpg"> RPG <br> <br>

<label for="quantita">Quantità:</label>
<input id="quantita" type="number" name="quantita" min="1" required><br><br>

<label for="prezzo">Prezzo:</label>
<input id="prezzo" type="text" name="prezzo" required><br><br>

<label for="copertina">Copertina:</label>
<input id="copertina" class="file" type="file" name="copertina" value="" maxlength="255" required><br><br>

<div id="form-button">
<input type="submit" value="Upload">
<input type="reset" value="Reset">
</div>

</form>
</div>

</body>
</html>