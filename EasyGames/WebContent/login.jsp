<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<title>Login</title>
</head>
<body>

<%
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>

<form action="Login" method="post"> 
<fieldset>
     <legend>Login Custom</legend>
     <label for="username">Username</label>
     <input id="username" type="text" name="username" placeholder="enter username">
     <br>   
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="enter password">
     <br>
     <input type="submit" value="Login"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form>
<a href="registration.jsp"><button>Registrati</button></a>


</body>
</html>