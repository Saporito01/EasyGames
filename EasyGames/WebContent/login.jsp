<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/login_register.css"/>
<title>Login</title>
</head>
<body>

<%
List<String> errors = (List<String>) request.getAttribute("errors");
String str = "";
if (errors != null){
	for (String error: errors){ %>		
			<%str = str + error;%>
<%
	}
}
%>

<header>
<a href="/EasyGames/"><img src="./images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<form class="login-form" action="Login" method="post"> 
<fieldset>
     <legend id="login-legend">Login</legend>
     <label for="username">Username</label>
     <input class="form-input" id="username" type="text" name="username" placeholder="enter username">
     <br>   
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="enter password">
     <br>
     <input type="submit" class="form-button" value="Login"/>
     <span id="error"><%=str%></span>
</fieldset>
</form>

<div class="registration-link"><a href="registration.jsp"><button class="registration-button">Registrati</button></a></div>

</body>
</html>