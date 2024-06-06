<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/login_register.css"/>
<title>Registrazione</title>
</head>
<body>

<header>
<a href="/EasyGames/"><img src="./images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<form class="login-form" action="account" method="post"> 
<fieldset>
     <legend id="login-legend">Registrati</legend>
     <label for="nickname">Nickname:</label>
     <input id="nickname" type="text" name="nickname">
     <br>
     <label for="nome">Nome:</label>
     <input id="nome" type="text" name="nome">
     <br>
     <label for="cognome">Cognome:</label>
     <input id="cognome" type="text" name="cognome">
     <br>
     <label for="dataNascita">Data di nascita:</label>
     <input id="dataNascita" type="date" name="dataNascita">
     <br>
     <label for="email">E-mail:</label>
     <input id="email" type="email" name="email">
     <br>
     <label for="password">Password:</label>
     <input id="password" type="password" name="password">
     <br>
     <input type="submit" class="form-button" value="Registrati"/>
</fieldset>
</form>
</body>
</html>