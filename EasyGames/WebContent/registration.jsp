<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/login_register.css"/>
<script src="script/validateForm.js"></script>
<title>Registrazione</title>
</head>
<body>

<header>
<a href="/EasyGames/"><img src="./images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<form id="regForm" class="login-form" action="account" method="post" onsubmit="return validate()"> 
<fieldset>
     <legend id="login-legend">Registrati</legend>
     <br>
     <label for="nickname">Nickname:</label>
     <input id="nickname" type="text" name="nickname">
     <span id="errorNickname"></span>
     <br>
     <br>
     <label for="nome">Nome:</label>
     <input id="nome" type="text" name="nome">
     <span id="errorName"></span>
     <br>
     <br>
     <label for="cognome">Cognome:</label>
     <input id="cognome" type="text" name="cognome">
     <span id="errorSurname"></span>
     <br>
     <br>
     <label for="dataNascita">Data di nascita:</label>
     <input id="dataNascita" type="date" name="dataNascita">
     <br>
     <br>
     <label for="email">E-mail:</label>
     <input id="email" type="email" name="email">
     <span id="errorEmail"></span>
     <br>
     <br>
     <label for="password">Password:</label>
     <input id="password" type="password" name="password">
     <span id="errorPassword"></span>
     <br>
     <br>
     <input type="submit" class="form-button" value="Registrati"/>
</fieldset>
</form>
</body>
</html>