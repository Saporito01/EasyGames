<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="../images/logo_scheda.png"/>
<title>Inserimento giochi</title>
</head>
<body>

<header>
<a href="/EasyGames/"><img src="../images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<div class="main">

<form action="" enctype="multipart/form-data" method="post">

Id: <input type="text" name="id" required><br><br>

Nome: <input type="text" name="nome" required><br><br>

Descrizione: <textarea name="descrizione" required></textarea><br><br>

Piattaforma: <select name="piattaforma" required>
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

Genere: <br>
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

Quantità: <input type="number" name="quantita" min="1" required><br><br>

Prezzo: <input type="text" name="prezzo" required><br><br>

Carica copertina:
<input class="file" type="file" name="copertina" value="" maxlength="255" required><br><br>

<input type="submit" value="Upload">
<input type="reset" value="Reset">

</form>

<a href="gestione.jsp"><button>TORNA ALLA HOME ADMIN</button></a>

</div>

</body>
</html>