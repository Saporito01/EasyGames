<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<title>Registrazione</title>
</head>
<body>
<form action="account" method="post"> 
<fieldset>
     <legend>Login Custom</legend>
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
     <input type="submit" value="Registrati"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form>
</body>
</html>